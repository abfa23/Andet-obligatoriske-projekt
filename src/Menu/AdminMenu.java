package Menu;

import InvestmentClub.ScannerHelper;
import InvestmentClub.StockHandling;

public class AdminMenu {
    ScannerHelper sh = new ScannerHelper();
    public void MainMenu() {
        boolean isDone = false;

        while (!isDone) {
            adminInterface();
            int userChoice = sh.askNumber(3);
            switch (userChoice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
            }
        }
    }

    public void adminInterface() {
        System.out.println();
        System.out.println("""
                           Velkommen til admin menuen!
                ┌────────────────────────────────────────────────────────────────┐
                │ Tryk  1 for at se en oversigt over brugernes portoføljeværdier.│
                │ Tryk  2 for at få en rangliste over brugerne.                  │
                │ Tryk  3 for at få vist en fordelinger på aktier og sektorer.   │
                └────────────────────────────────────────────────────────────────┘
                """);
    }
}
