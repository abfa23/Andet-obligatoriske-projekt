package InvestmentClub;

import FileHandler.ReadStockMarket;

import FileHandler.ReadUsers;
import Menu.*;
import java.util.ArrayList;

public class InvestmentClubTest {
    ReadUsers fh = new ReadUsers();
    ArrayList<String[]> users = fh.reader();

    public static void main(String[] args) {
        InvestmentClubTest test = new InvestmentClubTest();
        UserMenu userMenu = new UserMenu();
        AdminMenu adminMenu = new AdminMenu();
        InvestmentClubTest ict = new InvestmentClubTest();
        UserLogin uh = new UserLogin(ict.users);

        uh.login();
        userMenu.UserMainMenu();
        adminMenu.MainMenu();
    }
}

