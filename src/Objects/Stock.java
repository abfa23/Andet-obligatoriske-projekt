package Objects;

import InvestmentClub.StockHandling;
import java.util.Comparator;

public class Stock /*implements Comparable<Stock>*/ {
    private double price;
    private String name;
    private String ticker;
    private String sector;
    private String currency;
    private String rating;
    private double dividendYield;
    private String market;
    private String lastUpdated;

    public Stock(double price, String ticker, String name, String sector, String currency, String rating, double dividendYield, String market, String lastUpdated) {
        this.price = price;
        this.ticker = ticker;
        this.sector = sector;
        this.name = name;
        this.currency = currency;
        this.rating = rating;
        this.dividendYield = dividendYield;
        this.market = market;
        this.lastUpdated = lastUpdated;
    }


    public double getPrice() {
        return price;
    }

    public String getTicker() {
        return ticker;
    }

    public String getSector() { return sector; }

    public String getName() {
        return name;
    }

    public String getCurrency() { return currency;}

    public String getRating() { return rating;}

    public double getDividendYield() { return dividendYield; }

    public String getMarket() { return market; }

    public String getLastUpdated() { return lastUpdated; }

    public static Comparator<Stock> AlphabeticalByName =
            Comparator.comparing(Stock::getName, String.CASE_INSENSITIVE_ORDER);

    @Override
    public String toString() {
        return "Ticker: " + ticker + " | Aktie: " + name +  " | Pris: " + price + " " + currency + " | Sektorer: " + sector + " | Marked: " + market + " | Aktierating: " + rating;
    }
}