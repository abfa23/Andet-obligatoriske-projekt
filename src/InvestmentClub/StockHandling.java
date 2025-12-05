package InvestmentClub;

import Entities.Portfolio;
import Entities.Stock;
import Entities.Transaction;
import Entities.User;
import FileHandler.ReadTransactions;
import FileHandler.WriteTransactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class StockHandling {
    public static ArrayList<Stock> stocksList = new ArrayList<>();
    public int nextTransactionID;

    public StockHandling(ArrayList<String[]> stockData) {
        makeStocks(stockData, stocksList);
        stocksList.sort(Stock.AlphabeticalByName);
        this.nextTransactionID = 23;
    }

    public StockHandling() {
    }
    //Objekt som bruges i metode til at vise aktiemarkedet
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

    public String currentDate() { //Metode til at vise dato, formateret
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return localDate.format(format);
    }

    public void displayStockMarket() { //metode til at vise aktiemarkedet
        UIHelper.printStockMarket();

        for (Stock s : stocksList) {
            System.out.printf(Locale.GERMANY, "│ %-25s │ %-10s │ %,10.2f %-3s │ %-20s │ %-7s │%n",
                    s.getName().length() > 25 ? s.getName().substring(0, 22) + "..." : s.getName(),
                    s.getTicker(),
                    s.getPrice(),
                    s.getCurrency(),
                    s.getSector(),
                    s.getRating());
        }
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════\n");
    }

    public void buyStock(User currentUser, PortfolioHandling portfolioHandling) {
        displayStockMarket(); //metode som viser aktiemarkedet

        Stock selectedStock = null;

        //Hvis attributten selectedstock er null, så spørger programmet det følgende vha. scannerHelperen.
        while (selectedStock == null) {
            String ticker = UserLogin.sc.askQuestion("Indtast ticker på den aktie du vil købe");

            //Aktien findes via metoden findStock
            selectedStock = portfolioHandling.findStock(ticker.trim().toUpperCase());

            if (selectedStock == null) {
                System.out.println("Fejl med aktien: '" + ticker + "' findes ikke");
            }
        }

        int shares = 0;
        boolean isValid = false;

        //Mens attributten isValid er true, spørger programmet følgende
        while (!isValid) {
            String sharesInput = UserLogin.sc.askQuestion("Hvor mange aktier vil du købe?");

            //Input validation: Hvis attributten sharesInput er null, udskriver programmet følgende
            if (sharesInput == null) {
                System.out.println("Fejl: Feltet må ikke være tomt");
                continue;
            }

            //Input validation
            if (!InputHandler.ValidateInputIsInt(sharesInput)) {
                System.out.println("Fejl: Du skal indtaste et tal");
                continue;
            }

            //Parser attribut int shares til at være String så man kan sætte det i lig med String sharesInput
            shares = Integer.parseInt(sharesInput);

            //input validering
            if (shares <= 0) {
                System.out.println("Fejl: Du skal købe mindst 1 aktie");
                continue;
            }
            isValid = true;
        }

        //Kører igennem portfolio listen og finder userID via getter
        Portfolio p = null;
        for (Portfolio portfolio : portfolioHandling.portfolioList) {
            if (portfolio.getUserID() == currentUser.getUserID()) {
                p = portfolio;
                break;
            }
        }

        //input validering
        if (p == null) {
            System.out.println("Fejl: Kunne ikke finde portfolio for userID: " + currentUser.getUserID());
            return;
        }
        //totalPrice attribut som ganger getPrice med shares.
        double totalPrice = selectedStock.getPrice() * shares;

        UIHelper.printBuySummary(selectedStock, shares, totalPrice, p.getBalance());

        if (!InputHandler.validateEnoughCash(p, totalPrice)) {
            System.out.println("Fejl: Du har ikke nok penge til at købe " + shares + " aktier.");
            System.out.println("Din kontantbeholdning: " + p.getBalance() + " DKK");
            System.out.println("Mangler: " + (totalPrice - p.getBalance()));
            return;
        }

        //Input validering til bekræft af køb
        boolean validConfirmation = false;
        while (!validConfirmation) {
            String confirmation = UserLogin.sc.askQuestion("Vil du bekræfte købet? (ja/nej)");
            //input validering
            if (confirmation == null) {
                System.out.println("Fejl: Du skal indtaste ja eller nej");
                continue;
            }

            confirmation = confirmation.trim();
            //Input validering til annullering af køb
            if (confirmation.equalsIgnoreCase("ja")) {
                validConfirmation = true;
            } else if (confirmation.equalsIgnoreCase("nej")) {
                System.out.println("Køb annulleret");
                return;
            } else {
                System.out.println("Fejl: Ugydligt input. Indtast venligst ja eller nej");
            }
        }
        //Laver en transaction objekt
        Transaction transaction = new Transaction(
                nextTransactionID,
                currentUser.getUserID(),
                currentDate(),
                selectedStock.getTicker(),
                selectedStock.getPrice(),
                selectedStock.getCurrency(),
                "buy",
                shares
        );
        //Bruger writer til at skrive til transaction CSV via objektet ovenover.
        WriteTransactions writer = new WriteTransactions(transaction, this);
        writer.writer();

        // Reload alt
        ReadTransactions readTrans = new ReadTransactions();
        TransactionHistory transactionHistory = new TransactionHistory(readTrans.reader());
        portfolioHandling.transactions = transactionHistory.transactions;

        // Fjerner gammel data
        portfolioHandling.portfolioList.clear();
        portfolioHandling.calculatePortfolio();


        nextTransactionID++; //Hver gang et køb laves, vil denne attribut gemme købet som et tal i en kronologisk rækkefølge
        System.out.println("\nKøb gennemført!");
        System.out.println("Du har købt " + shares + " aktier af " + selectedStock.getTicker());
    }

    public void sellStock(User currentUser, PortfolioHandling portfolioHandling) {

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

        Stock selectedStock = null;
        String ticker = "";
        int currentShares = 0;

        boolean validTicker = false;
        while (!validTicker) {
            ticker = UserLogin.sc.askQuestion("Indtast ticker på den aktie du vil sælge");

            if (ticker == null) {
                System.out.println("Fejl: Feltet må ikke være tomt");
                continue;
            }

            ticker = ticker.trim().toUpperCase();

            if (!p.getHoldings().containsKey(ticker)) {
                System.out.println("Fejl: Du ejer ikke aktier af '" + ticker + "'");
                continue;
            }

            selectedStock = portfolioHandling.findStock(ticker);
            if (selectedStock == null) {
                System.out.println("Fejl: Aktien '" + ticker + "' findes ikke i markedet");
                continue;
            }

            currentShares = p.getHoldings().get(ticker);
            System.out.println("Du ejer " + currentShares + " aktier af " + ticker);
            validTicker = true;
        }

        int shares = 0;
        boolean validShares = false;

        while (!validShares) {
            String sharesInput = UserLogin.sc.askQuestion("Hvor mange aktier vil du sælge?");

            if (sharesInput == null) {
                System.out.println("Fejl: Feltet må ikke være tomt");
                continue;
            }

            if (!InputHandler.ValidateInputIsInt(sharesInput)) {
                System.out.println("Fejl: Du skal indtaste et tal");
                continue;
            }

            shares = Integer.parseInt(sharesInput);

            if (shares <= 0) {
                System.out.println("Fejl: Du skal sælge mindst 1 aktie");
                continue;
            }

            if (shares > currentShares) {
                System.out.println("Fejl: Du kan ikke sælge flere aktier end du ejer");
                System.out.println("Du ejer kun " + currentShares + " aktier af " + ticker);
                continue;
            }

            validShares = true;
        }

        double totalPrice = selectedStock.getPrice() * shares;

        UIHelper.printSellSummary(selectedStock, shares, totalPrice, p.getBalance());

        boolean validConfirmation = false;
        while (!validConfirmation) {
            String confirmation = UserLogin.sc.askQuestion("Vil du bekræfte salget (ja/nej)");

            if (confirmation == null) {
                System.out.println("Fejl: Feltet må ikke være tomt");
                continue;
            }

            if (confirmation.equalsIgnoreCase("Ja")) {
                validConfirmation = true;
            } else if (confirmation.equalsIgnoreCase("Nej")) {
                System.out.println("Salg annulleret");
                return;
            } else {
                System.out.println("Fejl: Ugydligt input. Indtast venligst ja eller nej");
            }
        }

        Transaction transaction = new Transaction(
                nextTransactionID,
                currentUser.getUserID(),
                currentDate(),
                ticker,
                selectedStock.getPrice(),
                selectedStock.getCurrency(),
                "sell",
                shares
        );

        WriteTransactions writer = new WriteTransactions(transaction, this);
        writer.writer();

        // Reloader alt
        ReadTransactions readTrans = new ReadTransactions();
        TransactionHistory transactionHistory = new TransactionHistory(readTrans.reader());
        portfolioHandling.transactions = transactionHistory.transactions;

        // Fjerner gammel data
        portfolioHandling.portfolioList.clear();
        portfolioHandling.calculatePortfolio();

        nextTransactionID++;
        System.out.println("\nSalg gennemført!");
        System.out.println("Du har solgt " + shares + " aktier af " + ticker);
    }
}