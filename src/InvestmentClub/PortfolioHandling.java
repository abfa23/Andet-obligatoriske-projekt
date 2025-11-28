package InvestmentClub;

import Objects.Portfolio;
import Objects.Stock;
import Objects.Transaction;
import Objects.User;

import java.util.ArrayList;

public class PortfolioHandling {
    public ArrayList<Portfolio> portfolioList = new ArrayList<>();

    ArrayList<User> users;
    ArrayList<Transaction> transactions;
    ArrayList<Stock> stocksList;

    public void calculatePortfolio() {
        PortfolioHandling ph = new PortfolioHandling();
        for (User u : users) {
            int userID = u.getUserID();
            double calcBalance = u.getInitialCash();

            for (Transaction t : transactions) {
                if (t.getUserID() == userID) {
                    if (t.getOrderType().equalsIgnoreCase("buy")) {
                        double actualBalance = (calcBalance - (t.getPrice() * t.getBoughtShares()));
                        Portfolio p = new Portfolio(userID, actualBalance);
                        portfolioList.add(p);
                        System.out.println(p);
                    } else {
                        double actualBalance = (calcBalance + (t.getPrice() * t.getBoughtShares()));
                        Portfolio p = new Portfolio(userID, actualBalance);
                        portfolioList.add(p);
                        System.out.println(p);
                    }
                }
            }
        }
    }
}
