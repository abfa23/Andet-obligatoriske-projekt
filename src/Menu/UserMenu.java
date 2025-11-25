package Menu;

import InvestmentClub.ScannerHelper;
import InvestmentClub.StockHandling;

public class UserMenu {
    ScannerHelper sh = new ScannerHelper();
    StockHandling stockHandling = new StockHandling();
    public void UserMainMenu() {
        boolean isDone = false;

        while (!isDone) {
            UserInterface();
            int userChoice = sh.askNumber(3);
            switch (userChoice) {
                case 1:
                    stockHandling.StockMarket();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

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
                │ Tryk  2 for at registrere køb af aktie.          │
                │ Tryk  3 for at se portofølje.                    │
                │ Tryk  4 for at se transaktionshistorik.          │
                └──────────────────────────────────────────────────┘
                """);
    }
}
