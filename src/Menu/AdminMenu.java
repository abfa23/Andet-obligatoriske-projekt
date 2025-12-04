package Menu;

import Entities.Transaction;
import FileHandler.ReadStockMarket;
import FileHandler.ReadTransactions;
import FileHandler.ReadUsers;
import InvestmentClub.*;

public class AdminMenu {
    ScannerHelper sh = new ScannerHelper();

    ReadStockMarket readMarket = new ReadStockMarket();
    StockHandling stockHandling = new StockHandling(readMarket.reader());

    ReadTransactions readTrans = new ReadTransactions();
    TransactionHistory transactionHistory = new TransactionHistory(readTrans.reader());

    ReadUsers ru = new ReadUsers();
    UserLogin userLogin = new UserLogin(ru.reader());

    PortfolioHandling ph;

    public AdminMenu() {
        ph = new PortfolioHandling(userLogin.users, transactionHistory.transactions, stockHandling.stocksList);
        ph.calculatePortfolio();

        stockHandling.nextTransactionID = getNextTransactionID();
    }

    // beregner næste transaktionid nummer
    private int getNextTransactionID() {
        int maxID = 0;
        for (Transaction t : transactionHistory.transactions) {
            if (t.getTransactionID() > maxID) {
                maxID = t.getTransactionID();
            }
        }
        return maxID + 1;
    }

    public void AdminMainMenu() {
        boolean isDone = false;

        while (!isDone) {
            UIHelper.displayAdminMenu();
            int userChoice = sh.askNumber(5);
            System.out.println();

            switch (userChoice) {
                case 1:
                    ph.displayPortfolioAdmin();
                    UIHelper.waitForEnter();
                    break;
                case 2:
                    ph.displayRanking();
                    UIHelper.waitForEnter();
                    break;
                case 3:
                    ph.showStockStatistics();
                    UIHelper.waitForEnter();
                    break;
                case 4:
                    userLogin.logout();
                    isDone = true;
                    break;

                case 5:
                    System.out.println("Lukker ned...");
                    System.exit(0);
                    break;
            }
        }
    }
}

