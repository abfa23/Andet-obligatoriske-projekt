package InvestmentClub;

import FileHandler.ReadStockMarket;
import java.util.ArrayList;
import Objects.Stock;

public class StockHandling {

    public ArrayList<Stock> stocksList = new ArrayList<>();

    public StockHandling(ArrayList<String[]> stockData) {
        makeStocks(stockData, stocksList);
    }

    public StockHandling() {

    }

    public void makeStocks(ArrayList<String[]> stockData, ArrayList<Stock> stocksList) {
        for (String [] strings : stockData) {
            double price = Double.parseDouble(strings[3]);
            String ticker = strings[0];
            String sector = strings[2];
            String name = strings[1];
            String currency = strings[4];
            String rating = strings[5];
            double dividendYield = Double.parseDouble(strings[6]);
            String market = strings[7];
            String lastUpdated = strings[8];

            Stock stocks = new Stock(price, ticker, sector, name, currency, rating, dividendYield, market, lastUpdated);
            stocksList.add(stocks);
        }
    }

    public void StockMarket() {

        System.out.println("Her vises aktiemarkedet: ");
        for (Stock s : stocksList) {
            System.out.println(s);
        }
    }

    public void sellStock() {

    }

    public void buyStock() {

    }
}
