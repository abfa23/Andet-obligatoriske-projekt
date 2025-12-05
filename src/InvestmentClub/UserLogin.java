package InvestmentClub;

import Entities.Admin;
import Entities.User;
import Menu.AdminMenu;
import Menu.UserMenu;

import java.util.ArrayList;

import static InvestmentClub.StockHandling.stocksList;

public class UserLogin {
    public static ScannerHelper sc = new ScannerHelper();
    //ved succesfuld login gemmer user objekter og id af personen logget ind
    private static User currentUser;
    private static int currentUserID;
    //arraylist af users, lavet ud fra reader string arraylist i makeUsers
    public ArrayList<User> users = new ArrayList<>();

    //constructor der modtager reader string arraylist
    public UserLogin(ArrayList<String[]> data) {
        makeUsers(data, users);
    }

    public UserLogin() {}

    public static User getCurrentUser() {
        return currentUser;
    }

    public static int getCurrentUserID() {
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
        boolean isDone = false;

        while (true) {
            UIHelper.displayLoginMenu();
            int choiceInput = sc.askNumber(3);
            System.out.println();

            //viderestiller baseret på input til login menu.
            switch (choiceInput) {
                case 1:
                    userLogin();
                    break;
                case 2:
                    adminLogin();
                    break;
                case 3:
                    System.out.println("\nLukker ned...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nUventet fejl!");
                    login();
                    break;
            }
        }
    }

    //bruger login
    public void userLogin() {
        UserMenu um = new UserMenu();
        boolean loggedIn = false;

        while (!loggedIn) {
            String emailInput = sc.askQuestion("Indtast venligst din email for at logge ind");
            //tjekker hvis emailen indtastet matcher en i users og kommer videre, hvis der er
            for (User s : users) {
                if (s.getEmail().equalsIgnoreCase(emailInput)) {
                    currentUser = s;
                    currentUserID = s.getUserID();
                    loggedIn = true;
                    break;
                }
            }
            //fejlet og prøver igen med nyt login
            if (!loggedIn) {
                System.out.println("Kan ikke finde email, prøv igen!");
            }
        }

        um.UserMainMenu();
    }

    //admin login
    public void adminLogin() {
        Admin a = new Admin("admin", "admin123");
        AdminMenu am = new AdminMenu();
        boolean loggedIn = false;

        while (!loggedIn) {
            String usernameInput = sc.askQuestion("Indtast admin brugernavn");

            //tjekker hvis username er admin username
            if (!a.getUsername().equals(usernameInput)) {
                System.out.println("Brugernavnet matcher ikke databasen. Forsøg igen!");
                continue;
            } else {
                System.out.println("Korrekt brugernavn");
            }

            String passwordInput = sc.askQuestion("Indtast venligst admin password");

            //tjekker om password er admin password
            if (passwordInput.equals(a.getPassword())) {
                loggedIn = true;
                System.out.println("\nLogget ind som admin");
            } else {
                System.out.println("Password matcher ikke databasen. Forsøg igen!");
            }
        }
        am.AdminMainMenu();
    }

    public void logout() {
        UserLogin ul = new UserLogin();
        StockHandling  sh = new StockHandling();
        System.out.println("Logger ud...");

        // clear alle program informationer
        currentUser = null;
        currentUserID = 0;
        ul.users.clear();
        stocksList.clear();

        System.out.println("Du er nu logget ud.");
    }
}

