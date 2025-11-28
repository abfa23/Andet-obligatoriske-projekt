package Objects;

public class Portfolio {
    private int userID;
    private double balance;

    public Portfolio(int userID, double balance) {
        this.userID = userID;
        this.balance = balance;
    }

    public int getUserID() {
        return userID;
    }
    public double getBalance() {
        return balance;
    }
}
