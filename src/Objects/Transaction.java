package Objects;

public class Transaction {
    private int transactionID;
    private int userID;
    private String date;
    private String ticker;
    private String price;
    private String currency;
    private String orderType;
    private int boughtShares;

    public Transaction(int transactionID, int userID, String date, String ticker, String price, String currency, String orderType, int boughtShares) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.date = date;
        this.ticker = ticker;
        this.price = price;
        this.currency = currency;
        this.orderType = orderType;
        this.boughtShares = boughtShares;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getUserID() {
        return userID;
    }

    public String getDate() {
        return date;
    }

    public String getTicker() {
        return ticker;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOrderType() {
        return orderType;
    }

    public int getBoughtShares() {
        return boughtShares;
    }

    @Override
    public String toString() {
        return date + " " + ticker + " " + price + " " + currency + " " + orderType + " " + boughtShares;
    }
}
