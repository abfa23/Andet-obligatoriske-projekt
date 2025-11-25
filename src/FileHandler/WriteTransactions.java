package FileHandler;

import Objects.Transactions;

public class WriteTransactions implements CSVWriter {

    public void writer() {

    }

    public String format(Transactions toWrite) {
        String id = Integer.toString(toWrite.getTransactionID());
        String userID = Integer.toString(toWrite.getUserID().getUserID());
        String date = toWrite.getDate().toString();
        String ticker = toWrite.getTicker();
        String price = Double.toString(toWrite.getPrice());
        String orderType =
    }
}
