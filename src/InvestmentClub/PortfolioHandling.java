package InvestmentClub;

import Entities.Portfolio;
import Entities.Stock;
import Entities.Transaction;
import Entities.User;

import java.util.*;

public class PortfolioHandling {
    public ArrayList<Portfolio> portfolioList = new ArrayList<>();

    private ArrayList<User> users;
    public ArrayList<Transaction> transactions;
    private ArrayList<Stock> stocksList;

    public PortfolioHandling(ArrayList<User> users, ArrayList<Transaction> transactions, ArrayList<Stock> stocksList) {
        this.users = users;
        this.transactions = transactions;
        this.stocksList = stocksList;
    }

    public void calculatePortfolio() {
        for (User u : users) {
            int userID = u.getUserID();
            double calcBalance = u.getInitialCash();

            Portfolio p = new Portfolio(userID, 0);

            for (Transaction t : transactions) {
                if (t.getUserID() == userID) {
                    if (t.getOrderType().equalsIgnoreCase("buy")) {
                        calcBalance = calcBalance - (t.getPrice() * t.getBoughtShares());
                        p.addHolding(t.getTicker(), t.getBoughtShares());

                    } else if (t.getOrderType().equalsIgnoreCase("sell")) {
                        calcBalance = calcBalance + (t.getPrice() * t.getBoughtShares());
                        p.removeHolding(t.getTicker(), t.getBoughtShares());
                    }
                }
            }
            p.setBalance(calcBalance);
            portfolioList.add(p);
        }
        calculateTotalValue();
    }

    public void calculateTotalValue() {
        for (Portfolio p : portfolioList) {
            double totalValue = p.getBalance();

            for (HashMap.Entry<String, Integer> e : p.getHoldings().entrySet()) {
                String ticker = e.getKey();
                int shares = e.getValue();

                Stock s = findStock(ticker);

                double stockPrice = s.getPrice();
                double dividendYield = s.getDividendYield();

                double stockValue = stockPrice * shares;

                double annualDividendYield = (stockValue * dividendYield) / 100;

                totalValue += stockValue + annualDividendYield;
            }

            p.setTotalValue(totalValue);
        }
    }

    public void displayRanking() {
        ArrayList<Portfolio> sortedPortfolios = new ArrayList<>(portfolioList);
        Collections.sort(sortedPortfolios);

        UIHelper.printHeader("RANGLISTE - SAMLET FORMUE");

        System.out.printf("│ %-6s │ %-34s │ %-31s │%n", "Rang", "Navn", "Formue");
        System.out.println("├────────┼────────────────────────────────────┼─────────────────────────────────┤");

        int rank = 1;
        for (Portfolio p : sortedPortfolios) {
            User u = findUser(p.getUserID());

            System.out.printf(Locale.GERMANY, "│ %-6d │ %-34s │ %,27.2f DKK │%n",
                    rank++, u.getFullName(), p.getTotalValue());
        }
        UIHelper.printDoubleLine();
    }


    public void displayPortfolio(int userID) {
        Portfolio userPortfolio = null;

        for (Portfolio p : portfolioList) {
            if (p.getUserID() == userID) {
                userPortfolio = p;
                break;
            }
        }

        if (userPortfolio == null) {
            throw new ExceptionHandler("userPortfolio == null, fejl i programmet. \n" +
                    "linje 60 - PortfolioHandling");
        }

        UIHelper.printHeader("DIT PORTEFØLJE");
        System.out.printf(Locale.GERMANY, "│ Kontantbeholdning: %,15.2f DKK %38s │%n", userPortfolio.getBalance(), "");
        System.out.printf(Locale.GERMANY, "│ Samlet Formue:     %,15.2f DKK %38s │%n", userPortfolio.getTotalValue(), "");
        UIHelper.printSingleLine();

        HashMap<String, Integer> holdings = userPortfolio.getHoldings();
        if (holdings.isEmpty()) {
            System.out.println("│ Du har ingen aktier i din portefølje                                           │");
            UIHelper.printDoubleLine();
            System.out.println();
            return;
        }

        System.out.printf("│ %-10s │ %-10s │ %-15s │ %-15s │ %-15s │%n",
                "Ticker", "Antal", "Pris/Aktie", "Total Værdi", "Årlig Stigning");
        System.out.println("├────────────┼────────────┼─────────────────┼─────────────────┼─────────────────┤");

        for (HashMap.Entry<String, Integer> e : holdings.entrySet()) {
            String ticker = e.getKey();
            int shares = e.getValue();

            Stock s = findStock(ticker);
            double stockPrice = s.getPrice();
            double stockValueTotal = stockPrice * shares;
            String currency = s.getCurrency();
            double dividendYield = s.getDividendYield();

            System.out.printf(Locale.GERMANY, "│ %-10s │ %,10d │ %,11.2f %-3s │ %,11.2f %-3s │ %,14.2f%% │%n",
                    ticker, shares, stockPrice, currency, stockValueTotal, currency, dividendYield);
        }
        UIHelper.printDoubleLine();
        System.out.println();
    }

    public void displayPortfolioAdmin() {
        UIHelper.printHeader("SAMLET PORTEFØLJER FOR ALLE MEDLEMMER");

        for (Portfolio p : portfolioList) {
            User u = findUser(p.getUserID());

            UIHelper.printSingleLine();
            System.out.printf(Locale.GERMANY, "│ BrugerID: %-3d │ Navn: %-27s │ Kontant: %,12.2f DKK │%n",
                    p.getUserID(), u.getFullName(), p.getBalance());
            System.out.printf(Locale.GERMANY, "│ Formue: %,12.2f DKK %52s │ %n", p.getTotalValue(), "");
            UIHelper.printSingleLine();

            HashMap<String, Integer> holdings = p.getHoldings();

            if (holdings.isEmpty()) {
                System.out.println("│ Ingen aktier i portefølje                                                            │");
            } else {
                System.out.printf("│ %-10s │ %-10s │ %-15s │ %-15s │ %-15s │%n",
                        "Ticker", "Antal", "Pris/Aktie", "Total Værdi", "Årlig Stigning");
                System.out.println("├────────────┼────────────┼─────────────────┼─────────────────┼─────────────────┤");

                for (HashMap.Entry<String, Integer> e : holdings.entrySet()) {
                    String ticker = e.getKey();
                    int shares = e.getValue();

                    Stock s = findStock(ticker);
                    double stockPrice = s.getPrice();
                    double stockValueTotal = stockPrice * shares;
                    String currency = s.getCurrency();
                    double dividendYield = s.getDividendYield();

                    System.out.printf(Locale.GERMANY, "│ %-10s │ %,10d │ %,11.2f %-3s │ %,11.2f %-3s │ %,14.2f%% │%n",
                            ticker, shares, stockPrice, currency, stockValueTotal, currency, dividendYield);
                }
            }
            UIHelper.printSingleLine();
        }
        System.out.println();
    }

    public void showStockStatistics() {
        HashMap<String, Integer> stock = new HashMap<>();
        HashMap<String, Integer> sector = new HashMap<>();

        UIHelper.printHeader("AKTIESTATISTIK - SEKTORER OG AKTIER");

        for (Portfolio p : portfolioList) {
            HashMap<String, Integer> holdings = p.getHoldings();
            if (holdings.isEmpty()) {
                continue;
            }

            for (Map.Entry<String, Integer> e : holdings.entrySet()) {
                String ticker = e.getKey();
                int shares = e.getValue();

                //aktie fordeling
                stock.put(ticker, (stock.getOrDefault(ticker, 0) + shares));

                //sektorer fordeling
                Stock s = findStock(ticker);
                String stockSector = s.getSector();
                sector.put(stockSector, (sector.getOrDefault(stockSector, 0) + shares));
            }
        }

        System.out.println("│                                SEKTOR STATISTIK                               │");
        System.out.println("├───────────────────────────────────────────────┬───────────────────────────────┤");
        System.out.printf("│ %-45s │ %-29s │%n", "Sektor navn", "Antal aktier købt i sektor");
        System.out.println("├───────────────────────────────────────────────┼───────────────────────────────┤");

        for (HashMap.Entry<String, Integer> e : sector.entrySet()) {
            System.out.printf(Locale.GERMANY, "│ %-45s │ %,29d │%n", e.getKey(), e.getValue());
        }

        System.out.println("─────────────────────────────────────────────────────────────────────────────────");
        System.out.println("│                                AKTIE STATISTIK                                │");
        System.out.println("├───────────────────────────────────────────────┬───────────────────────────────┤");
        System.out.printf("│ %-45s │ %-29s │%n", "Ticker", "Antal aktier/bruger");
        System.out.println("├───────────────────────────────────────────────┼───────────────────────────────┤");

        for (HashMap.Entry<String, Integer> e : stock.entrySet()) {
            System.out.printf(Locale.GERMANY, "│ %-45s │ %,29d │%n", e.getKey(), e.getValue());
        }

        UIHelper.printDoubleLine();
    }


    //hjælpe metode til at finde aktie der passer med ticker for at kunne bruge getters til aktien til display metoden.
    public Stock findStock(String ticker) {
        for (Stock s : stocksList) {
            if (s.getTicker().equalsIgnoreCase(ticker)) {
                return s;
            }
        }
        return null;
    }

    //hjælpe metode til at finde user der passer med user bruges til displayAdmin metoden.
    public User findUser(int userID) {
        for (User u : users) {
            if (u.getUserID() == userID) {
                return u;
            }
        }
        return null;
    }
}
