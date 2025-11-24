package InvestmentClub;

import User.User;

import java.util.Scanner;

public class UserHandling {

    public void login() {
        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;

        //log ind som user eller 2 for logge ind som bruger
        System.out.println("Skriv 1 for at logge ind som bruger, og skriv 2 som at logge ind som admin.");
        String valgInput = sc.nextLine();

        if (valgInput.equalsIgnoreCase("1")) {
            userLogin();
        } else if (valgInput.equalsIgnoreCase("2")) {
            adminLogin();
        } else {
            System.out.println("Du har hverken skrevet 1 eller 2. Prøv igen!");
            login();
        }

        //login with user
        System.out.println("Indtast venligst din email for at logge ind.");
        String emailInput = sc.nextLine();

        //tjekker hvis emailen indtastet matcher en i users og ændre logged ind til true.
        while (!loggedIn) {
            for (User s : users) {
                if (s.getEmail().equalsIgnoreCase(emailInput)) {
                    loggedIn = true;
                    System.out.println("Logged in successfully!");
                }
            }
        }
    }

    public void userLogin() {
        boolean loggedIn = false;

    }

    public void adminLogin() {
        boolean loggedIn = false;

    }
}
