package InvestmentClub;
import java.util.Comparator;

class Compare implements Comparable {
    //place holder string
    public int totalValue;

    public int compareTo(Object u) {
        User user = (User)u;
        if(totalValue < user.totalValue) return -1;
        if(totalValue > user.totalValue) return 1;
        return 0;
    }
}
