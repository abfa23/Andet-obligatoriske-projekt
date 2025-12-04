package InvestmentClub;

import FileHandler.ReadUsers;

import java.util.ArrayList;

public class InvestmentClubTest {
    ReadUsers fh = new ReadUsers();
    ArrayList<String[]> users = fh.reader();

    public static void main(String[] args) {
        InvestmentClubTest ict = new InvestmentClubTest();
        UserLogin uh = new UserLogin(ict.users);
        uh.login();
    }
}