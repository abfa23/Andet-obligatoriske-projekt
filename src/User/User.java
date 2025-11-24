package User;

public class User {
    private int userID;
    private String fullName;
    private String email;
    private String birthDate;
    private double initialCash;
    private String createdAt;
    private String lastUpdated;
    private int

    public User(int userID, String fullName, String email, String birthDate, double initialCash, String createdAt, String lastUpdated) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.initialCash = initialCash;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this
    }

    public int getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public double getInitialCash() {
        return initialCash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setInitialCash(double initialCash) {
        this.initialCash = initialCash;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString () {
        return userID + " " + fullName + " " + email + " " + birthDate + " " + initialCash + " " + createdAt + " " + lastUpdated;
    }
}
