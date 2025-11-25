package FileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCurrency implements CSVReader {
    ArrayList<String[]> formatedData = new ArrayList<>();

    @Override
    public ArrayList<String[]> reader(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/FileHandler/Database/currency.csv"))){
            String line;
            //læser første linje inden while-loop (første linje i CSV filen bliver kasseret)
            bufferedReader.readLine();

            while((line = bufferedReader.readLine()) != null){
                formatedData.add(format(line));
            }
        }catch ( FileNotFoundException e){
            System.out.println("currency.csv does not exist.");
        }catch ( IOException e){
            System.out.println("ERROR: IOException");
        }
        return formatedData;
    }

    @Override
    public String[] format(String s) {
        return s.split(";");
    }
}
