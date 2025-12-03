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

    public void MainMenu() {
        boolean isDone = false;

        while (!isDone) {
            adminInterface();
            int userChoice = sh.askNumber(5);
            switch (userChoice) {
                case 1:
                    ph.displayPortfolioAdmin();
                    break;
                case 2:
                    ph.displayRanking();
                    break;
                case 3:

                    break;
                case 4:

                    break;

                case 5:
                    System.out.println("lukker ned...");
                    System.exit(0);
                    break;

            }
        }
    }

    public void adminInterface() {
        System.out.println();
        System.out.println("""
                           Velkommen til admin menuen!
                ┌────────────────────────────────────────────────────────────────┐
                │ Tryk  1 for at se en oversigt over brugernes porteføljeværdier.│
                │ Tryk  2 for at få en rangliste over brugerne.                  │
                │ Tryk  3 for at få vist en fordelinger på aktier og sektorer.   │
                │ Tryk  4 for at logge ud.                                       │
                │ Tryk  5 for at luk ned.                                        │
                └────────────────────────────────────────────────────────────────┘
                """);
    }
}

