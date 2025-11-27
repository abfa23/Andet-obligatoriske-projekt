package Menu;

import FileHandler.ReadStockMarket;
import FileHandler.ReadTransactions;
import InvestmentClub.ScannerHelper;
import InvestmentClub.StockHandling;
import InvestmentClub.TransactionHistory;

import java.util.ArrayList;

public class UserMenu {
    ScannerHelper sh = new ScannerHelper();

    ReadStockMarket readMarket = new ReadStockMarket();
    StockHandling stockHandling = new StockHandling(readMarket.reader());

    ReadTransactions readTrans = new ReadTransactions();
    TransactionHistory transactionHistory = new TransactionHistory(readTrans.reader());

    public void UserMainMenu() {
        boolean isDone = false;

        while (!isDone) {
            UserInterface();
            int userChoice = sh.askNumber(5);
            switch (userChoice) {
                case 1:
                    stockHandling.StockMarket();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    transactionHistory.printTransactionHistory();
                    break;
            }
        }
    }

    public void UserInterface() {
        System.out.println();
        System.out.println("""
                           Velkommen til brugermenuen!
                ┌──────────────────────────────────────────────────┐
                │ Tryk  1 for at se aktiemarked og aktuel kurs.    │
                │ Tryk  2 for at registrere køb af aktier.         │
                │ Tryk  3 for at registrere salg af aktier         │
                │ Tryk  4 for at se portofølje.                    │
                │ Tryk  5 for at se transaktionshistorik.          │
                └──────────────────────────────────────────────────┘
                """);
    }
}
