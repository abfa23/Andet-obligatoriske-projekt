package InvestmentClub;

import Objects.Stock;

import java.util.ArrayList;
import java.util.Collections;

public class Portfolio {
    private ArrayList<Stock> stocks = new ArrayList<>();

    public void addStocks(Stock stock) {
        stocks.add(stock);
    }

//    public void sortStocks() {
//        Collections.sort(stocks);
//    }

    public void userStockIndex() {
        for (int i = 0; i < stocks.size(); i++) {
            System.out.println(i + 1 + ": " + stocks.get(i).toString());
        }
    }
}

