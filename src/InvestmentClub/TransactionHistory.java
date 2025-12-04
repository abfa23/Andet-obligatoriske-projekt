package InvestmentClub;

import Entities.Transaction;

import java.util.ArrayList;
import java.util.Locale;

import static InvestmentClub.UserLogin.getCurrentUserID;

public class TransactionHistory {
    public ArrayList<Transaction> transactions = new ArrayList<>();

    public TransactionHistory(ArrayList<String[]> transData) {
        makeTransactionObjects(transData, transactions);
    }

    //laver transactions arraylisten med transaction objekter ud fra reader data
    public void makeTransactionObjects(ArrayList<String[]> transData, ArrayList<Transaction> transactions) {

        for (String[] strings : transData) {
            if (strings.length == 0 || strings[0].trim().isEmpty()) {
                continue;
            }

            if (strings.length < 8) {
                System.out.println("Warning: Skipping incomplete transaction row");
                continue;
            }

            int transactionID = Integer.parseInt(strings[0]);
            int userID = Integer.parseInt(strings[1]);
            String date = strings[2];
            String ticker = strings[3];
            double price = Double.parseDouble(strings[4]);
            String currency = strings[5];
            String orderType = strings[6];
            int boughtShares = Integer.parseInt(strings[7]);

            Transaction transaction = new Transaction(transactionID, userID, date, ticker, price, currency, orderType, boughtShares);
            transactions.add(transaction);
        }
    }

    //  printer transactions ud for medlem logget ind
    public void displayTransactionHistory() {
        UIHelper.printHeader("DIN TRANSAKTIONSHISTORIK");
        System.out.printf("│ %-13s │ %-12s │ %-8s │ %-10s │ %-7s │ %-12s │%n",
                "Ticker", "Pris", "Valuta", "Køb/Salg", "Antal", "Dato");
        System.out.println("├───────────────┼──────────────┼──────────┼────────────┼─────────┼──────────────┤");

        for (Transaction t : transactions) {
            if (t.getUserID() == getCurrentUserID()) {
                System.out.printf(Locale.GERMANY, "│ %-13s │ %,12.2f │ %-8s │ %-10s │ %,7d │ %-12s │%n",
                        t.getTicker(),
                        t.getPrice(),
                        t.getCurrency(),
                        t.getOrderType().equalsIgnoreCase("buy") ? "Køb" : "Salg",
                        t.getBoughtShares(),
                        t.getDate());
            }
        }
        UIHelper.printDoubleLine();
    }
}

