package FileHandler;

import InvestmentClub.StockHandling;
import Objects.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

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
            writer.newLine();
        } catch (IOException e) {
            System.out.println("ERROR: IOException");
        }
    }

    public String format(Transaction toFormat) {
        String id = Integer.toString(toFormat.getTransactionID());
//        String userID = Integer.toString(toWrite.getUserID().getUserID());
        String date = stockHandling.currentDate();
        String ticker = toFormat.getTicker();
        double price = toFormat.getPrice();
//        String orderType =
        String shares = Integer.toString(toFormat.getBoughtShares());

        return id + ";" + /*userID +*/ ";" + date + ";" + ticker + ";" + price + ";" + /*orderType +*/ ";" + shares;
    }
}
