package InvestmentClub;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import FileHandler.ReadTransactions;
import FileHandler.WriteTransactions;
import Entities.Portfolio;
import Entities.Stock;
import Entities.Transaction;
import Entities.User;
import InvestmentClub.UIHelper.*;

public class StockHandling {
    public ArrayList<Portfolio> portfolioList = new ArrayList<>();
    private int nextTransactionID;
    public static ArrayList<Stock> stocksList = new ArrayList<>();

    public StockHandling(ArrayList<String[]> stockData) {
        makeStocks(stockData, stocksList);
        stocksList.sort(Stock.AlphabeticalByName);
        int startpoint = 22;
        this.nextTransactionID = startpoint + (nextTransactionID + 1);
    }

    public StockHandling() {

    }

    public void makeStocks(ArrayList<String[]> stockData, ArrayList<Stock> stocksList) {
        for (String[] strings : stockData) {
            double price = Double.parseDouble(strings[3]);
            String ticker = strings[0];
            String sector = strings[2];
            String name = strings[1];
            String currency = strings[4];
            String rating = strings[5];
            double dividendYield = Double.parseDouble(strings[6]);
            String market = strings[7];
            String lastUpdated = strings[8];

            Stock stocks = new Stock(price, ticker, name, sector, currency, rating, dividendYield, market, lastUpdated);
            stocksList.add(stocks);
        }
    }

    public String currentDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return localDate.format(format);
    }

    public Portfolio findPortfolio(int userID) {
        for (Portfolio p : portfolioList)
            if (p.getUserID() == userID) {
                return p;
            }
        return null;
    }


    public void buyStock(User currentUser, PortfolioHandling portfolioHandling) {
//        System.out.println("""
//                ═════════════════════════════════════════════════════════════════════════════════
//                                                   KØB AKTIER\s
//                ═════════════════════════════════════════════════════════════════════════════════""");

        UIHelper.printBuyHeader();
        UIHelper.displayStockMarket();

        Stock selectedStock = null;

        while (selectedStock == null) {
            String ticker = UserLogin.sc.askQuestion("Indtast ticker på den aktie du vil købe.");
            selectedStock = portfolioHandling.findStock(ticker);

            if (selectedStock == null) {
                System.out.println("Fejl med aktien: '" + ticker + "' findes ikke.");
            }
        }


        String sharesInput = UserLogin.sc.askQuestion("Hvor mange aktier vil du købe?");

        if (!InputHandler.ValidateInputIsInt(sharesInput)) {
            System.out.println("Fejl: Du skal indtaste et heltal.");
            return;
        }

        int shares = Integer.parseInt(sharesInput);

        if (shares <= 0) {
            System.out.println("Fejl: Du skal købe mindst 1 aktie.");
            return;

        }

        Portfolio p = null;
        for (Portfolio portfolio : portfolioHandling.portfolioList) {
            if (portfolio.getUserID() == currentUser.getUserID()) {
                p = portfolio;
                break;
            }
        }

        if (p == null) {
            System.out.println("Fejl: Kunne ikke finde portfolio for userID: " + currentUser.getUserID());
            return;
        }

        double totalPrice = selectedStock.getPrice() * shares;

        UIHelper.printBuySummary(selectedStock, shares, totalPrice, p.getBalance());

//        System.out.println("Total pris: " + totalPrice + " " + selectedStock.getCurrency());

        if (!InputHandler.validateEnoughCash(p, totalPrice)) {
            System.out.println("Fejl: Du har ikke nok penge til at købe " + shares + " aktier.");
            System.out.println("Din kontantbeholdning: " + p.getBalance() + " DKK");
            System.out.println("Mangler: " + (totalPrice - p.getBalance()));
            return;
        }

        String confirmation = UserLogin.sc.askQuestion("Vil du bekræfte købet? (ja/nej)");
        if (!confirmation.equalsIgnoreCase("Ja")) {
            System.out.println("Køb annulleret");
            return;
        }

        Transaction transaction = new Transaction(nextTransactionID, currentUser.getUserID(), currentDate(), selectedStock.getTicker(), selectedStock.getPrice(), selectedStock.getCurrency(), "buy", shares);
        WriteTransactions writer = new WriteTransactions(transaction, this);
        writer.writer();


        ReadTransactions readTrans = new ReadTransactions();
        TransactionHistory transactionHistory = new TransactionHistory(readTrans.reader());
        portfolioHandling.transactions = transactionHistory.transactions;
        portfolioHandling.calculatePortfolio();

        // opdatere StockHandling's portfolioList
        this.portfolioList = portfolioHandling.portfolioList;

        p = null;
        for (Portfolio portfolio : portfolioHandling.portfolioList) {
            if (portfolio.getUserID() == currentUser.getUserID()) {
                p = portfolio;
                break;
            }
        }

        nextTransactionID++;
        System.out.println("\n Køb gennemført!");
        System.out.println("Du har købt " + shares + " aktier af " + selectedStock.getTicker());
    }

    public void sellStock(User currentUser, PortfolioHandling portfolioHandling) {
        System.out.println("\n--- salg af aktier ---");

        Portfolio p = null;
        for (Portfolio portfolio : portfolioHandling.portfolioList) {
            if (portfolio.getUserID() == currentUser.getUserID()) {
                p = portfolio;
                break;
            }
        }

        if (p == null) {
            System.out.println("Fejl: Kunne ikke finde portfolio for userID: " + currentUser.getUserID());
            return;
        }

        portfolioHandling.displayPortfolio(currentUser.getUserID());

        if (p.getHoldings().isEmpty()) {
            System.out.println("Du har ingen aktier at sælge!");
            return;
        }

        String ticker = UserLogin.sc.askQuestion("Indtast ticker på den aktie du vil sælge");

        if (!p.getHoldings().containsKey(ticker)) {
            System.out.println("Fejl: Du ejer ikke aktier af '" + ticker + "'.");
            return;
        }

        Stock selectedStock = portfolioHandling.findStock(ticker);
        if (selectedStock == null) {
            System.out.println("Fejl: Aktien '" + ticker + "' findes ikke i markedet.");
            return;
        }

        int currentShares = p.getHoldings().get(ticker);
        System.out.println("Du ejer " + currentShares + " aktier af " + ticker);

        String sharesInput = UserLogin.sc.askQuestion("Hvor mange aktier vil du sælge?");

        if (!InputHandler.ValidateInputIsInt(sharesInput)) {
            System.out.println("Fejl: Du skal indtaste et heltal.");
            return;
        }
        int shares = Integer.parseInt(sharesInput);

        if (shares <= 0) {
            System.out.println("Fejl: Du skal sælge mindst 1 aktie.");
            return;
        }

        if (shares > currentShares) {
            System.out.println("Fejl: Du kan ikke sælge flere aktier end du ejer.");
            System.out.println("Du ejer kun " + currentShares + " aktier af " + ticker);
            return;
        }

        double totalPrice = selectedStock.getPrice() * shares;

        System.out.println("Total salgspris: " + totalPrice + " " + selectedStock.getCurrency());
        System.out.println("Din nuværende kontantbeholdning: " + p.getBalance() + " DKK");

        String confirmation = UserLogin.sc.askQuestion("Vil du bekræfte salget (ja/nej)");
        if (!confirmation.equalsIgnoreCase("Ja")) {
            System.out.println("Salg annulleret");
            return;
        }

        Transaction transaction = new Transaction(nextTransactionID, currentUser.getUserID(), currentDate(), ticker, selectedStock.getPrice(), selectedStock.getCurrency(), "Sell", shares);
        WriteTransactions writer = new WriteTransactions(transaction, this);
        writer.writer();

        // Genkører hele processen
        ReadTransactions readTrans = new ReadTransactions();
        TransactionHistory transactionHistory = new TransactionHistory(readTrans.reader());
        portfolioHandling.transactions = transactionHistory.transactions;
        portfolioHandling.calculatePortfolio();

        // Opdaterer StockHandling's portfolioList
        this.portfolioList = portfolioHandling.portfolioList;

        // Ny portfolio reference
        p = null;
        for (Portfolio portfolio : portfolioHandling.portfolioList) {
            if (portfolio.getUserID() == currentUser.getUserID()) {
                p = portfolio;
                break;
            }
        }

        nextTransactionID++;

        System.out.println("\nSalg gennemført.");
        System.out.println("Du har solgt " + shares + " aktier af " + ticker);
    }

}