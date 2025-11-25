package InvestmentClub;

import FileHandler.ReadStockMarket;

import java.util.ArrayList;

public class InvestmentClubTest {
    ScannerHelper sh = new ScannerHelper();

    public static void main(String[] args) {
        InvestmentClubTest test = new InvestmentClubTest();
        UserHandling uh = new UserHandling();
        uh.login();
        test.printMainMenu();
        test.printUserMainMenu();
    }

    // Denne metode kører programmet og er "hub" for metoderne.
    public void printMainMenu() {
        boolean isDone = false;

        while (!isDone) {
            printMainMenu();
            int userChoice = sh.askNumber(4);
            switch (userChoice) {
                case 1:

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

    public void printUserMainMenu() {
        boolean isDone = false;

        while (!isDone) {
            printUserMainMenu();
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
}

