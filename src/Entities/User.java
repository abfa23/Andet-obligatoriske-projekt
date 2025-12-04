package Entities;

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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public double getInitialCash() {
        return initialCash;
    }

    public void setInitialCash(double initialCash) {
        this.initialCash = initialCash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return userID + " " + fullName + " " + email + " " + birthDate + " " + initialCash + " " + createdAt + " " + lastUpdated;
    }
}
