package InvestmentClub;

import Menu.AdminMenu;
import Menu.UserMenu;
import Objects.User;
import Objects.Admin;
import java.util.ArrayList;
import java.util.Scanner;

public class UserLogin {
    //ved succesfuld login gemmer user objekter og id af personen logget ind
    private User currentUser;
    private int currentUserID;

    //arraylist af users, lavet ud fra reader string arraylist i makeUsers
    public ArrayList<User> users = new ArrayList<>();

    //constructor der modtager reader string arraylist
    public UserLogin(ArrayList<String[]> data) {
        makeUsers(data, users);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public int getCurrentUserID() {
        return currentUserID;
    }

    //lav string arraylist om til objekt arraylist
    public void makeUsers(ArrayList<String[]> data, ArrayList<User> users) {
        for (String[] strings : data) {
            int userID = Integer.parseInt(strings[0]);
            String fullName = strings[1];
            String email = strings[2];
            String birthDate = strings[3];
            double initialCash = Double.parseDouble(strings[4]);
            String createdAt = strings[5];
            String lastUpdated = strings[6];

            User user = new User(userID, fullName, email, birthDate, initialCash, createdAt, lastUpdated);
            users.add(user);
        }
    }

    //valg af slags login
    public void login() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Skriv 1 for at logge ind som bruger eller skriv 2 for at logge ind som admin.");
        String choiceInput = sc.nextLine();

        //viderestiller baseret på input til login menu.
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

    //bruger login
    public void userLogin() {
        Scanner sc = new Scanner(System.in);
        UserMenu um = new UserMenu();

        System.out.println("Indtast venligst din email for at logge ind.");
        String emailInput = sc.nextLine();

        //tjekker hvis emailen indtastet matcher en i users og kommer videre, hvis der er
        for (User s : users) {
            if (s.getEmail().equalsIgnoreCase(emailInput)) {
                System.out.println("Logged ind som bruger.");
                this.currentUser = s;
                this.currentUserID = s.getUserID();
                um.UserMainMenu();
                return;
            }
        }
        //fejlet og prøver igen med nyt login
        System.out.println("Kan ikke finde email, prøv igen!");
        userLogin();
    }

    //admin login
    public void adminLogin() {
        Scanner sc = new Scanner(System.in);
        Admin a = new Admin("admin", "admin123");
        AdminMenu am = new AdminMenu();
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("Indtast admin brugernavn.");
            String usernameInput = sc.nextLine();

            //tjekker hvis username er admin username
            if (!a.getUsername().equals(usernameInput)) {
                System.out.println("Brugernavnet matcher ikke databasen. Forsøg igen!");
                continue;
            } else {
                System.out.println("Korrekt brugernavn.");
            }

            System.out.println("Indtast venligst admin password.");
            String passwordInput = sc.nextLine();

            //tjekker om password er admin password
            if (passwordInput.equals(a.getPassword())) {
                loggedIn = true;
                System.out.println("Logged ind som admin.");
            } else {
                System.out.println("Password matcher ikke databasen. Forsøg igen!");
            }
        }
        am.MainMenu();
    }
}

