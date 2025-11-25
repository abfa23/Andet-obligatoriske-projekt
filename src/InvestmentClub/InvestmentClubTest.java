package InvestmentClub;

import FileHandler.ReadStockMarket;

import Menu.*;
import java.util.ArrayList;

public class InvestmentClubTest {

    public static void main(String[] args) {
        InvestmentClubTest test = new InvestmentClubTest();
        UserMenu userMenu = new UserMenu();
        AdminMenu adminMenu = new AdminMenu();

        userMenu.UserMainMenu();
        adminMenu.MainMenu();
    }
}

