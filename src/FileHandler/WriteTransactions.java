package FileHandler;

import InvestmentClub.StockHandling;
import Entities.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTransactions implements CSVWriter {
    Transaction toWrite;
    private StockHandling stockHandling;

    public WriteTransactions(Transaction toWrite, StockHandling stockHandling) {
        this.toWrite = toWrite;
        this.stockHandling = stockHandling;
    }

    @Override
    public void writer() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/FileHandler/Database/transactions.csv", true))) {
            writer.write(format(toWrite));
        } catch (IOException e) {
            System.out.println("ERROR: IOException");
        }
    }

    public String format(Transaction toFormat) {
        String id = Integer.toString(toFormat.getTransactionID());
        String userID = Integer.toString(toWrite.getUserID());
        String date = stockHandling.currentDate();
        String ticker = toFormat.getTicker().toUpperCase();
        double price = toFormat.getPrice();
        String currency = toFormat.getCurrency();
        String orderType = toFormat.getOrderType();
        String shares = Integer.toString(toFormat.getBoughtShares());

        return "\n" + id + ";" + userID + ";" + date + ";" + ticker + ";" + price + ";" + currency + ";" + orderType + ";" + shares;
    }
}