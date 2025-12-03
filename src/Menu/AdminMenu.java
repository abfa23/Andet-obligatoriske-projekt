package Menu;

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
        ph = new PortfolioHandling(userLogin.users,
                transactionHistory.transactions, stockHandling.stocksList);
        ph.calculatePortfolio();
    }

    public void AdminMainMenu() {
        boolean isDone = false;

        while (!isDone) {
//            adminInterface();
            UIHelper.displayAdminMenu();
            int userChoice = sh.askNumber(5);
            UIHelper.printBlankLine();

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
                    isDone =  true;
                    break;

                case 5:
                    System.out.println("lukker ned...");
                    System.exit(0);
                    break;
            }
        }
    }

//    public void adminInterface() {
//        System.out.println();
//        System.out.println("""
//                ═════════════════════════════════════════════════════════════════════════════════
//                                                   ADMIN MENU\s
//                ═════════════════════════════════════════════════════════════════════════════════
//                │                                                                               │
//                │  [1] 📊  Se oversigt over alle brugeres porteføljer                           │
//                │                                                                               │
//                │  [2] 🏆  Vis rangliste over brugere                                           │
//                │                                                                               │
//                │  [3] 📈  Se fordeling på aktier og sektorer                                   │
//                │                                                                               │
//                │  [4] 🚪  Log ud                                                               │
//                │                                                                               │
//                │  [5] ❌  Luk programmet                                                       │
//                │                                                                               │
//                ═════════════════════════════════════════════════════════════════════════════════
//                """);
//        System.out.print("Vælg venligst en mulighed (1-5): ");
//    }
}

