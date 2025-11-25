package InvestmentClub;

import FileHandler.ReadUsers;
import java.util.ArrayList;

public class UserHandlingTest {
    ReadUsers fh = new ReadUsers();
    ArrayList<String[]> users = fh.reader();

    public static void main(String[] args) {
        UserHandlingTest uht = new UserHandlingTest();
        UserHandling uh = new UserHandling(uht.users);
        uh.login();

    }
}
