package InvestmentClub;

public class Stocks {

    int stock;
    String name;

    public Stocks (int stock, String name) {
        this.stock = stock;
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public void setStock (int stock) {
        this.stock = stock;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String toString () {
        return stock + " " + name + ".";
    }
}