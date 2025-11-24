package User;

public class Admin {
    private String username;
    private String password;

    public Admin (String username, String password) {
        this.username = "admin";
        this.password = "admin123";
    }

    public Admin() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username + " " + password;
    }
}