package Menu;

import Entities.Transaction;
import FileHandler.ReadStockMarket;
import FileHandler.ReadTransactions;
import FileHandler.ReadUsers;
import InvestmentClub.*;

public class UserMenu {
    ScannerHelper sh = new ScannerHelper();

    ReadStockMarket readMarket = new ReadStockMarket();
    StockHandling stockHandling = new StockHandling(readMarket.reader());

    ReadTransactions readTrans = new ReadTransactions();
    TransactionHistory transactionHistory = new TransactionHistory(readTrans.reader());

    ReadUsers ru = new ReadUsers();
    UserLogin userLogin = new UserLogin(ru.reader());

    PortfolioHandling ph;

    public UserMenu() {
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

    public void UserMainMenu() {
        boolean isDone = false;

        while (!isDone) {
            //UserInterface();
            UIHelper.displayUserMenu(UserLogin.getCurrentUser());
            int userChoice = sh.askNumber(7);
            System.out.println();

            switch (userChoice) {
                case 1:
                    UIHelper.displayStockMarket();
                    UIHelper.waitForEnter();
                    break;
                case 2:
                    stockHandling.buyStock(UserLogin.getCurrentUser(), ph);
                    UIHelper.waitForEnter();
                    break;
                case 3:
                    stockHandling.sellStock(UserLogin.getCurrentUser(), ph);
                    UIHelper.waitForEnter();
                    break;
                case 4:
                    ph.displayPortfolio(UserLogin.getCurrentUserID());
                    UIHelper.waitForEnter();
                    break;
                case 5:
                    //opdaterer, hvis ændringer lavet
                    ReadTransactions readTransAgain = new ReadTransactions();
                    transactionHistory = new TransactionHistory(readTransAgain.reader());
                    transactionHistory.displayTransactionHistory();
                    UIHelper.waitForEnter();
                    break;
                case 6:
                    userLogin.logout();
                    isDone = true;
                    break;
                case 7:
                    System.out.println("Lukker ned...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nUgyldigt valg! Prøv igen.");
                    break;
            }
        }
    }
}

