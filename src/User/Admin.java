package User;

public class Admin {
    private String username;
    private String password;

    public Admin (String username, String password) {
        this.username = "admin";
        this.password = "admin123";
    }

    @Override
    public String toString() {
        return username + " " + password;
    }
}