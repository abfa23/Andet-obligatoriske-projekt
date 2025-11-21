package FileHandler;

import java.util.ArrayList;

public interface CSVreader {
    public ArrayList<String[]> reader();
    public String[] format(String s);
}
