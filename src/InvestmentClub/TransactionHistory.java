package InvestmentClub;
import Objects.Transactions;
import InvestmentClub.UserLogin;

import java.util.ArrayList;

public class TransactionHistory {
    public ArrayList<Transactions> transactions = new ArrayList<>();
    private UserLogin ul = new UserLogin();

    public TransactionHistory(ArrayList<String[]> transData) {
        makeTransactionObjects(transData, transactions);
    }

    public void makeTransactionObjects(ArrayList<String[]> transData, ArrayList<Transactions> transactions) {
        for (String[] strings : transData) {
            int transactionID = Integer.parseInt(strings[0]);
            int userID = Integer.parseInt(strings[1]);
            String date = strings[2];
            String ticker = strings[3];
            String price = strings[4];
            String currency = strings[5];
            String orderType = strings[6];
            int boughtShares = Integer.parseInt(strings[7]);

            Transactions transaction = new Transactions(transactionID, userID, date, ticker, price, currency, orderType, boughtShares);
            transactions.add(transaction);
        }

    }

    //TODO- FIX TEST

    public void printTransactionHistory() {
        for (Transactions t : transactions) {
            if (t.getUserID() == ul.getCurrentUserID()) {
                System.out.println(t);
            }
            }
        }
    }

