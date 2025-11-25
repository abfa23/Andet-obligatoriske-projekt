package InvestmentClub;

import java.util.ArrayList;

public class Portofolio {
    private ArrayList<Stocks> Stocks = new ArrayList<>();

    public void addStocks(Stocks stocks) {
        Stocks.add(stocks);
    }

}

public void userStockIndex() {
    for (int i = 0; i < Stocks.size(); i++) {
        System.out.println(i + 1 + ": " + Stocks.get(i).toString());
    }
}

