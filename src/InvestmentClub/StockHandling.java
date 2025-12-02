package InvestmentClub;

import FileHandler.ReadStockMarket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import FileHandler.WriteTransactions;
import Objects.Portfolio;
import Objects.Stock;
import Objects.Transaction;
import Objects.User;

import javax.sound.sampled.Port;

public class StockHandling {
    public ArrayList<Portfolio> portfolioList = new ArrayList<>();
    private int nextTransactionID;
    private LocalDate localDate;
    public static ArrayList<Stock> stocksList = new ArrayList<>();

    public StockHandling(ArrayList<String[]> stockData) {
        makeStocks(stockData, stocksList);
        this.nextTransactionID = nextTransactionID + 1;
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

            Stock stocks = new Stock(price, ticker, sector, name, currency, rating, dividendYield, market, lastUpdated);
            stocksList.add(stocks);
        }
    }

    public static void StockMarket() {

        System.out.println("Her vises aktiemarkedet: ");
        for (Stock s : stocksList) {
            System.out.println(s);
        }
    }

    public String currentDate() {
        localDate = LocalDate.now();
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
        StockMarket();

        String ticker = UserLogin.sc.askQuestion("Indtast ticker på den aktie du vil købe.");
        Stock selectedStock = portfolioHandling.findStock(ticker);
        if (selectedStock == null) {
            System.out.println("Fejl med aktien: '" + ticker + "' findes ikke.");
        }
        String sharesInput = UserLogin.sc.askQuestion("Hvor mange aktier vil du købe?");

        if (!InputHandler.ValidateInputIsInt(sharesInput));
        System.out.println("Fejl: Du skal indtaste et heltal.");

        int shares = Integer.parseInt(sharesInput);

        if (shares <= 0) {
            System.out.println("Fejl: Du skal købe mindst 1 aktie.");

        }

        Portfolio p = findPortfolio(currentUser.getUserID());

        double totalPrice = selectedStock.getPrice() * shares;

        System.out.println("Total pris: " + totalPrice + " " + selectedStock.getCurrency());
        if (!InputHandler.validateEnoughCash(p, totalPrice)) {
            System.out.println("Fejl: Du har ikke nok penge til at købe " + shares + " aktier.");
            System.out.println("Din kontaktbeholdning: " + p.getBalance() + " DKK");
        }

        String confirmation = UserLogin.sc.askQuestion("Vil du bekræfte købet? (ja/nej)");
        if (!confirmation.equalsIgnoreCase("Nej")) {
            System.out.println("Køb annulleret");
        }
        Transaction transaction = new Transaction(nextTransactionID, currentUser.getUserID(), currentDate(), ticker, selectedStock.getPrice(), selectedStock.getCurrency(), "Buy", shares);

        WriteTransactions writer = new WriteTransactions(transaction, this);
        writer.writer();

        portfolioHandling.calculatePortfolio();

        nextTransactionID++;
        System.out.println("\n Køb gennemført!");
        System.out.println("Du har købt " + shares + " aktier af " + ticker);
        System.out.println("Ny kontaktbeholdning: " + p.getBalance());
    }

    public void sellStock(User currentUser, PortfolioHandling portfolioHandling) {
        System.out.println("\n--- salg af aktier ---");

        portfolioHandling.displayPortfolio(currentUser.getUserID());
    }
}