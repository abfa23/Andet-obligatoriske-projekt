package InvestmentClub;

import Objects.Portfolio;
import Objects.Stock;
import Objects.Transaction;
import Objects.User;

import java.util.ArrayList;

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

            for (Transaction t : transactions) {
                if (t.getUserID() == userID) {
                    if (t.getOrderType().equalsIgnoreCase("buy")) {
                        calcBalance = calcBalance - (t.getPrice() * t.getBoughtShares());
                    } else if (t.getOrderType().equalsIgnoreCase("sell")) {
                        calcBalance = (calcBalance + (t.getPrice() * t.getBoughtShares()));
                    }
                }
            }

        }
    }
}
