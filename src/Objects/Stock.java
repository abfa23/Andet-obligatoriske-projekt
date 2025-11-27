package Objects;

public class Stock implements Comparable<Stock> {
    private int price;
    private String name;
    private String ticker;
    private String sector;
    private String currency;
    private String rating;
    private double dividendYield;
    private String market;
    private String lastUpdated;

    public Stock(int price, String ticker, String name, String sector, String currency, String rating, double dividendYield, String market, String lastUpdated) {
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


    public int getPrice() {
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

    public double dividendYield() { return dividendYield; }

    public String getMarket() { return market; }

    public String getLastUpdated() { return lastUpdated; }

    @Override
    public int compareTo(Stock other) {
        return Integer.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return " Price: " + price + "ticker: " + ticker + "Sector: " + sector + "name: " + name + "market: " + market + "Last updated: " + lastUpdated + "Currency: " + currency + "Dividend yield" + dividendYield;
    }
}