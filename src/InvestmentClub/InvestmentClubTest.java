package InvestmentClub;

public class InvestmentClubTest {
    ScannerHelper sh = new ScannerHelper();

    public static void main(String[] args) {
        InvestmentClubTest test = new InvestmentClubTest();
        UserHandling uh = new UserHandling();
        uh.login();
        test.mainMenuProgram();
    }

    // Denne metode kører programmet og er "hub" for metoderne.
    public void mainMenuProgram() {
        boolean isDone = false;

        while (!isDone) {
            printMainMenu();
            int userChoice = sh.askNumber(11);
            switch (userChoice) {
                case 1:
                    break;

                case 2:
            }

        }
    }
    }

