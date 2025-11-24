package InvestmentClub;

import User.User;

import java.util.ArrayList;

public class UserHandlingTest {
    public static void main(String[] args) {

        //logik for at kunne teste UserHandling manuelt **ikke unit test**
        //skal fodre userhandling en arraylist.
        ArrayList<User> users = new ArrayList<>();
        User u1 = new User(1, "John Henriksen", "john@gmail.com",
                "03-01-2003", 1000, "24-11-2025", "24-11-2025");
        users.add(u1);

        UserHandling uh = new UserHandling(users);
        uh.login();

    }
}
