package InvestmentClub;

import Objects.Transaction;
import java.util.ArrayList;
import static InvestmentClub.UserLogin.getCurrentUserID;

public class TransactionHistory {
    public ArrayList<Transaction> transactions = new ArrayList<>();

    public TransactionHistory(ArrayList<String[]> transData) {
        makeTransactionObjects(transData, transactions);
    }

    public void makeTransactionObjects(ArrayList<String[]> transData, ArrayList<Transaction> transactions) {
        for (String[] strings : transData) {
            int transactionID = Integer.parseInt(strings[0]);
            int userID = Integer.parseInt(strings[1]);
            String date = strings[2];
            String ticker = strings[3];
            String price = strings[4];
            String currency = strings[5];
            String orderType = strings[6];
            int boughtShares = Integer.parseInt(strings[7]);

            Transaction transaction = new Transaction(transactionID, userID, date, ticker, price, currency, orderType, boughtShares);
            transactions.add(transaction);
        }

    }

    //TODO- FIX TEST

    public void printTransactionHistory() {
        for (Transaction t : transactions) {
            if (t.getUserID() == getCurrentUserID()) {
                System.out.println(t);
            }
            }
        }
    }

