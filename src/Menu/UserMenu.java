package Menu;

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
        ph = new PortfolioHandling(userLogin.users,
                transactionHistory.transactions, stockHandling.stocksList);
        ph.calculatePortfolio();
    }

    public void UserMainMenu() {
        boolean isDone = false;

        while (!isDone) {
            UserInterface();
            int userChoice = sh.askNumber(7);
            switch (userChoice) {
                case 1:
                    stockHandling.displayStockMarket();
                    break;
                case 2:
                    stockHandling.buyStock(UserLogin.getCurrentUser(), ph);
                    break;
                case 3:
                    stockHandling.sellStock(UserLogin.getCurrentUser(), ph);
                    break;
                case 4:
                    ph.displayPortfolio(UserLogin.getCurrentUserID());
                    break;
                case 5:
                    //opdaterer, hvis ændringer lavet
                    ReadTransactions readTransAgain = new ReadTransactions();
                    transactionHistory = new TransactionHistory(readTransAgain.reader());

                    transactionHistory.printTransactionHistory();
                    break;
                case 6:
                    userLogin.logout();
                    break;
                case 7:
                    System.out.println("lukker ned...");
                    System.exit(0);
                    break;
            }
        }
    }

    public void UserInterface() {
        System.out.println();
        System.out.println("""
                ═════════════════════════════════════════════════════════════════════════════════
                                                BRUGER MENU\s
                ═════════════════════════════════════════════════════════════════════════════════""");
        System.out.printf("│ 👤 Logget ind som: %-62s │%n", UserLogin.getCurrentUser().getFullName());
        System.out.println("""
                ═════════════════════════════════════════════════════════════════════════════════
                │                                                                               │
                │  [1] 📈  Se aktiemarked og aktuelle kurser                                    │
                │                                                                               │
                │  [2] 💰  Køb aktier                                                           │
                │                                                                               │
                │  [3] 💸  Sælg aktier                                                          │
                │                                                                               │
                │  [4] 📂  Se min portefølje                                                    │
                │                                                                               │
                │  [5] 📜  Se transaktionshistorik                                              │
                │                                                                               │
                │  [6] 🚪  Log ud                                                               │
                │                                                                               │
                │  [7] ❌  Luk programmet                                                       │
                │                                                                               │
                ═════════════════════════════════════════════════════════════════════════════════
                """);
        System.out.print("Vælg venligst en mulighed (1-7): ");
    }
}

