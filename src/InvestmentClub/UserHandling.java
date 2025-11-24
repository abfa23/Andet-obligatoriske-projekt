package InvestmentClub;

import User.User;
import User.Admin;
import java.util.ArrayList;
import java.util.Scanner;

public class UserHandling {
    private ArrayList<User> users = new ArrayList<>();

    public void login() {
        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;

        //log ind som user eller 2 for logge ind som bruger
        System.out.println("Skriv 1 for at logge ind som bruger, og skriv 2 som at logge ind som admin.");
        String valgInput = sc.nextLine();

        if (valgInput.equalsIgnoreCase("1")) {
            userLogin(users);
        } else if (valgInput.equalsIgnoreCase("2")) {
            adminLogin();
        } else {
            System.out.println("Du har hverken skrevet 1 eller 2. Prøv igen!");
            login();
        }
    }

    public void userLogin(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;

        //login med user
        System.out.println("Indtast venligst din email for at logge ind.");
        String emailInput = sc.nextLine();

        //tjekker hvis emailen indtastet matcher en i users og ændre logged ind til true.
        while (!loggedIn) {
            for (User s : users) {
                if (s.getEmail().equalsIgnoreCase(emailInput)) {
                    loggedIn = true;
                    System.out.println("Logged ind som bruger.");
                } else {
                    loggedIn = false;
                    userLogin(users);
                }
            }
        }
    }

    public void adminLogin() {
        Scanner sc = new Scanner(System.in);
        Admin a = new Admin();
        boolean loggedIn = false;

        //login med admin
        System.out.println("Indtast admin brugernavn.");
        String usernameInput = sc.nextLine();

        //tjekker hvis username er admin username.
        boolean correctUserName = false;
        if (a.getUsername().equals(usernameInput)) {
            correctUserName = true;
            System.out.println("Korrekt.");
        } else {
            System.out.println("Det er ikke det korrekte username. Kontakt IT, hvis du har glemt dit username!");
            adminLogin();
        }

        if (correctUserName == true) {
            System.out.println("Indtast admin password.");
            String passwordInput = sc.nextLine();

            if (passwordInput.equals(a.getPassword())) {
                loggedIn = true;
                System.out.println("Logged ind som admin.");
            }
        }

    }
}
