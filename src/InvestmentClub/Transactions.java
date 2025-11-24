package InvestmentClub;

import java.time.LocalDate;

public class Transactions {
    private int transactionID;
//    private int userID;
    private LocalDate date;
    private String ticker;
    private double price;
    private String orderType;
    private int boughtShares;

    public Transactions(int transactionID, LocalDate date, String ticker, double price, String orderType, int boughtShares) {
        this.transactionID = transactionID;
        this.date = date;
        this.ticker = ticker;
        this.price = price;
        this.orderType = orderType;
        this.boughtShares = boughtShares;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public String getOrderType() {
        return orderType;
    }

    public int getBoughtShares() {
        return boughtShares;
    }
}
