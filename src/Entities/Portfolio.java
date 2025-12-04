package Entities;

import java.util.HashMap;

public class Portfolio implements Comparable<Portfolio> {
    private int userID;
    private double balance;
    private HashMap<String, Integer> holdings;
    private double totalValue;

    public Portfolio(int userID, double balance) {
        this.userID = userID;
        this.balance = balance;
        this.holdings = new HashMap<>();
        this.totalValue = 0.0;
    }

    public int getUserID() {
        return userID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double input) {
        this.balance = input;
    }

    public HashMap<String, Integer> getHoldings() {
        return holdings;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
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
    public int compareTo(Portfolio other) {
        return Double.compare(other.totalValue, this.totalValue);
    }

    @Override
    public String toString() {
        return "BrugerID: " + userID + " Kontantbeholdning: " + balance + " Aktier: " + holdings.size();
    }

}
