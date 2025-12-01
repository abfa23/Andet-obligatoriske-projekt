package Objects;

import java.util.HashMap;

public class Portfolio {
    private int userID;
    private double balance;
    private HashMap<String, Integer> holdings;

    public Portfolio(int userID, double balance) {
        this.userID = userID;
        this.balance = balance;
        this.holdings = new HashMap<>();
    }

    public int getUserID() {
        return userID;
    }
    public double getBalance() {
        return balance;
    }

    public HashMap<String, Integer> getHoldings() {
        return holdings;
    }

    public void setBalance(double input) {
        this.balance = input;
    }

    public void addHolding(String ticker, int boughtShares) {
        holdings.put(ticker, holdings.getOrDefault(ticker, 0) + boughtShares);
    }

    public void removeHolding(String ticker, int boughtShares) {
        int currentHolding = holdings.getOrDefault(ticker, 0);
        if (currentHolding - boughtShares <= 0) {
            holdings.remove(ticker);
            } else {
            holdings.put(ticker, currentHolding - boughtShares);
        }
    }

    @Override
    public String toString() {
        return "BrugerID: " + userID + " Kontantbeholdning: " + balance + " Aktier: " + holdings.size();
    }

}
