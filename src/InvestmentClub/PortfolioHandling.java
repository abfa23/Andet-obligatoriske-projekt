package InvestmentClub;

import Objects.Portfolio;
import Objects.Stock;
import Objects.Transaction;
import Objects.User;

import java.util.ArrayList;
import java.util.HashMap;

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
    }

    public void displayPortfolio(int userID) {
        Portfolio userPortfolio = null;

        for (Portfolio p : portfolioList) {
            if (p.getUserID() == userID) {
                userPortfolio = p;
            }
        }

        // TODO Lav custom exception til her! Nedenunder. Der er flere muligheder!

        if (userPortfolio == null) {
            System.out.println("Ingen portfolio til brugeren");
            return;
        }

        System.out.println("Dette er dit portfolio: ");
        System.out.println("Dette er dit mønt!: " + userPortfolio.getBalance());

        HashMap<String, Integer> holdings = userPortfolio.getHoldings();
        if (holdings.isEmpty()) {
            System.out.println("Fejl.");
            return;
        }


        for (HashMap.Entry<String, Integer> e : holdings.entrySet()) {
            String ticker = e.getKey();
            int shares = e.getValue();

            System.out.println("Aktie: " + ticker + " Antal: " + shares);
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
}
