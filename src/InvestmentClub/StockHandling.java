package InvestmentClub;

import FileHandler.ReadStockMarket;
import java.util.ArrayList;

public class StockHandling {

    public void StockMarket() {
        ReadStockMarket market = new ReadStockMarket();
        ArrayList<String[]> stocks = market.reader();

        System.out.println("Her vises aktiemarkedet: ");
        for (String [] stock : stocks);
        System.out.println(stocks);
    }
}
