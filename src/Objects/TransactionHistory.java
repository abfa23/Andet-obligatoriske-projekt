package Objects;
import java.util.ArrayList;

public class TransactionHistory {
    public ArrayList<Transactions> transactionHistory = new ArrayList<>();

    public void addTransactions(Transactions transactions){
        transactionHistory.add(transactions);
    }
}
