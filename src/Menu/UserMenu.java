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
                    ph.displayPortfolio(UserLogin.getCurrentUserID());
                    break;
                case 5:
                    transactionHistory.printTransactionHistory();
                    break;
                case 6:

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
                           Velkommen til brugermenuen!
                ┌──────────────────────────────────────────────────┐
                │ Tryk  1 for at se aktiemarked og aktuel kurs.    │
                │ Tryk  2 for at registrere køb af aktier.         │
                │ Tryk  3 for at registrere salg af aktier         │
                │ Tryk  4 for at se portfolio.                     │
                │ Tryk  5 for at se transaktionshistorik.          │
                │ Tryk  6 fot at log ud.                           │
                │ Tryk  7 for at luk ned.                          │
                └──────────────────────────────────────────────────┘
                """);
    }
}
