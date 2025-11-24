package InvestmentClub;

import User.User;
import User.Admin;
import java.util.ArrayList;
import java.util.Scanner;

public class UserHandling {

    //får arraylist af users ind i klassen
    private ArrayList<User> users;

    public UserHandling(ArrayList<User> users) {
        this.users = users;
    }

    public void login() {
        Scanner sc = new Scanner(System.in);

        //log ind som user eller 2 for logge ind som bruger
        System.out.println("Skriv 1 for at logge ind som bruger, og skriv 2 som at logge ind som admin.");
        String choiceInput = sc.nextLine();

        switch (choiceInput) {
            case "1":
                userLogin();
                break;

            case "2":
                adminLogin();
                break;
            default:
                System.out.println("Skriv enten 1 eller 2, prøv igen.");
                login();
        }
    }

    public void userLogin() {
        Scanner sc = new Scanner(System.in);

        //login med user
        System.out.println("Indtast venligst din email for at logge ind.");
        String emailInput = sc.nextLine();

        //tjekker hvis emailen indtastet matcher en i users og kommer videre, hvis der er
        for (User s : users) {
            if (s.getEmail().equalsIgnoreCase(emailInput)) {
                System.out.println("Logged ind som bruger.");
                return;
            }
        }

        //fejlet og prøver igen med nyt login
        System.out.println("Kan ikke finde email, prøv igen!");
        userLogin();
    }

    public void adminLogin() {
        Scanner sc = new Scanner(System.in);
        Admin a = new Admin("admin", "admin123");
        boolean loggedIn = false;

        while (!loggedIn) {
            //login med admin
            System.out.println("Indtast admin brugernavn.");
            String usernameInput = sc.nextLine();

            //tjekker hvis username er admin username.
            if (!a.getUsername().equals(usernameInput)) {
                System.out.println("Brugernavnet matcher ikke databasen. Forsøg igen!");
                continue;
            } else {
                System.out.println("Korrekt brugernavn.");
            }

            System.out.println("Indtast venligst admin password.");
            String passwordInput = sc.nextLine();

            if (passwordInput.equals(a.getPassword())) {
                loggedIn = true;
                System.out.println("Logged ind som admin.");
            } else {
                System.out.println("Password matcher ikke databasen. Forsøg igen!");
            }
        }
    }
}

