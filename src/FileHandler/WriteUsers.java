package FileHandler;

import Objects.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteUsers implements CSVWriter {
    User toWrite;

    @Override
    public void writer() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/FileHandler/Database/users.csv"))) {
            bufferedWriter.write(format(User.))
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String format(User u) {
        String userID = Integer.toString(u.getUserID());
        String fullName = u.get
}
