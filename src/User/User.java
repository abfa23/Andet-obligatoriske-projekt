package User;

public class User {
    private int userID;
    private String fullName;
    private String email;
    private String birthDate;
    private double initialCash;
    private String createdAt;
    private String lastUpdated;

    public User(int userID, String fullName, String email, String birthDate, double initialCash, String createdAt, String lastUpdated) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.initialCash = initialCash;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString () {
        return userID + " " + fullName + " " + email + " " + birthDate + " " + initialCash + " " + createdAt + " " + lastUpdated;
    }
}
