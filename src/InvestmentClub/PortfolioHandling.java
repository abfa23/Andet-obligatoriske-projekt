package InvestmentClub;

import Objects.Portfolio;
import Objects.Stock;
import Objects.Transaction;
import Objects.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

public class PortfolioHandling {
    public ArrayList<Portfolio> portfolioList = new ArrayList<>();

    private ArrayList<User> users;
    private ArrayList<Transaction> transactions;
    private ArrayList<Stock> stocksList;

    public PortfolioHandling(ArrayList<User> users, ArrayList<Transaction> transactions, ArrayList<Stock> stocksList) {
        this.users = users;
        this.transactions = transactions;
        this.stocksList = stocksList;
    }

    public void calculatePortfolio() {
        for (User u : users) {
            int userID = u.getUserID();
            double calcBalance = u.getInitialCash();

            Portfolio p = new Portfolio(userID, 0);

            for (Transaction t : transactions) {
                if (t.getUserID() == userID) {
                    if (t.getOrderType().equalsIgnoreCase("buy")) {
                        calcBalance = calcBalance - (t.getPrice() * t.getBoughtShares());
                        p.addHolding(t.getTicker(), t.getBoughtShares());

                    } else if (t.getOrderType().equalsIgnoreCase("sell")) {
                        calcBalance = calcBalance + (t.getPrice() * t.getBoughtShares());
                        p.removeHolding(t.getTicker(), t.getBoughtShares());
                    }
                }
            }
            p.setBalance(calcBalance);
            portfolioList.add(p);
        }
        calculateTotalValue();
    }

    public void calculateTotalValue() {
        for (Portfolio p : portfolioList) {
            double totalValue = p.getBalance();

            for (HashMap.Entry<String, Integer> e : p.getHoldings().entrySet()) {
                String ticker = e.getKey();
                int shares = e.getValue();

                Stock s = findStock(ticker);

                double stockPrice = s.getPrice();
                double dividendYield = s.getDividendYield();

                double stockValue = stockPrice * shares;

                double annualDividendYield = (stockValue * dividendYield) / 100;

                totalValue += stockValue + annualDividendYield;
            }

            p.setTotalValue(totalValue);
        }
    }

    public void displayRanking() {
        ArrayList<Portfolio> sortedPortfolios = new ArrayList<>(portfolioList);
        Collections.sort(sortedPortfolios);

        System.out.println("┌───────────────────────────────────┐\n" +
                "│     RANGLISTE - SAMLET FORMUE     │\n" +
                "└───────────────────────────────────┘");
        System.out.printf("%-6s %-21s%-1s%n", "Rang", "Navn", "Formue");

        int rank = 1;
        for (Portfolio p : sortedPortfolios) {
            User u = findUser(p.getUserID());

            System.out.printf(Locale.GERMANY, "%-6d %-20s %,-10.2f%s%n", rank++, u.getFullName(), (p.getTotalValue()), "DKK");
        }

    }

    public void displayPortfolio(int userID) {
        Portfolio userPortfolio = null;

        for (Portfolio p : portfolioList) {
            if (p.getUserID() == userID) {
                userPortfolio = p;
            }
        }

        if (userPortfolio == null) {
            throw new ExceptionHandler("userPortfolio == null, fejl i programmet. \n" +
                    "linje 60 - PortfolioHandling");
        }

        System.out.println("┌─────────────────────────┐\n" +
                           "│ Dette er dit portefølje!│\n" +
                           "└─────────────────────────┘");
        System.out.printf(Locale.GERMANY, "Kontantbeholdning: %,.2f %s%n", userPortfolio.getBalance(), "DKK");
        System.out.printf(Locale.GERMANY, "Formue: %,.2f %s%n", userPortfolio.getTotalValue(), "DKK");

        HashMap<String, Integer> holdings = userPortfolio.getHoldings();
        if (holdings.isEmpty()) {
            System.out.println("Du har ingen aktier!");
            return;
        }

        for (HashMap.Entry<String, Integer> e : holdings.entrySet()) {
            String ticker = e.getKey();
            int shares = e.getValue();

            Stock s = findStock(ticker);
            double stockValueTotal = s.getPrice() * shares;

            System.out.println("Aktie: " + ticker + " | Antal: " + shares + " | Aktieværdi i alt: " +
                    stockValueTotal + s.getCurrency() + " | Årlig stigning: " + s.getDividendYield() + "% ");
        }
    }

    public void displayPortfolioAdmin() {
        System.out.println(
                "┌───────────────────────────────────┐\n" +
                "│ Her er alle brugernes porteføljer!│\n" +
                "└───────────────────────────────────┘");

        for (Portfolio p : portfolioList) {
            System.out.print("BrugerID: " + p.getUserID() + " | ");

            User u = findUser(p.getUserID());

            System.out.print("Fuldt navn: " + u.getFullName() + " | ");
            System.out.print("Kontantbeholdning: " + p.getBalance());

            HashMap<String, Integer> holdings = p.getHoldings();
            if (holdings.isEmpty()) {
                System.out.println("Bruger har ingen aktier!");
                return;
            }

            System.out.println(" ");
            for (HashMap.Entry<String, Integer> e : holdings.entrySet()) {
                String ticker = e.getKey();
                int shares = e.getValue();

                System.out.print("Aktie: " + ticker + " Antal: " + shares + " | ");
            }
            System.out.println("\n ");
        }
    }

    //hjælpe metode til at finde aktie der passer med ticker for at kunne bruge getters til aktien til display metoden.
    public Stock findStock(String ticker) {
        for (Stock s : stocksList) {
            if (s.getTicker().equalsIgnoreCase(ticker)) {
                return s;
            }
        }
        return null;
    }

    //hjælpe metode til at finde user der passer med user bruges til displayadmin metoden.
    public User findUser(int userID) {
        for (User u : users) {
            if (u.getUserID() == userID) {
                return u;
            }
        }
        return null;
    }
}
