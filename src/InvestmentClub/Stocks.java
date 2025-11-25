package InvestmentClub;

import com.sun.jdi.Value;
import java.util.Comparator;

public class Stocks implements Comparable<Stocks>{
    private int value;
    private int cost;
    private String name;

    public Stocks(int value, int cost, String name) {
        this.value = value;
        this.cost = cost;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setValue(){
        this.value = value;
    }
    public void setCost(){
        this.cost = cost;
    }
    public void setName(){
        this.name = name;
    }
    @Override
    public int compareTo(Stocks other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString(){
        return name + " Price: " + cost +" Value: "+ value;
    }
}
