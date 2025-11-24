package InvestmentClub;

import User.User;

import java.util.ArrayList;

public class UserHandlingTest {
    public static void main(String[] args) {

        //UserHandling login metode test
        UserHandling u = new UserHandling();
        ArrayList<User> users = new ArrayList<>();
        User u1 = new User(1, "John Henrik", "john123@gmail.com",
                "?", 0, "?", "?");

        users.add(u1);
        u.login();
    }
}
