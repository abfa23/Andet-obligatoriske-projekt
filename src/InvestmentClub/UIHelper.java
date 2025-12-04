package InvestmentClub;

import Entities.Transaction;
import Entities.User;
import Entities.Stock;

import java.util.ArrayList;
import java.util.Locale;

import static InvestmentClub.StockHandling.*;
import static InvestmentClub.UserLogin.getCurrentUserID;

public class UIHelper {
    private static final String DOUBLE_LINE = "═════════════════════════════════════════════════════════════════════════════════";
    private static final String SINGLE_LINE = "─────────────────────────────────────────────────────────────────────────────────";
    private static final int TABLE_WIDTH = 85;

    public static void displayLoginMenu() {
        System.out.println();
        System.out.println("""
                ═════════════════════════════════════════════════════════════════════════════════
                                      VELKOMMEN TIL INVESTERINGSKLUBBEN!\s
                ═════════════════════════════════════════════════════════════════════════════════
                │                                                                               │
                │  [1] 👤  Log ind som bruger                                                   │
                │                                                                               │
                │  [2] 🔐  Log ind som admin                                                    │
                │                                                                               │
                │  [3] ❌  Luk programmet                                                       │
                │                                                                               │
                ═════════════════════════════════════════════════════════════════════════════════
                """);
        System.out.print("Vælg venligst en mulighed (1-3): ");
    }

    public static void displayUserMenu(User currentUser) {
       System.out.println("""
                ═════════════════════════════════════════════════════════════════════════════════
                                                BRUGER MENU\s
                ═════════════════════════════════════════════════════════════════════════════════""");
        System.out.printf("│ 👤 Logget ind som: %-62s │%n", currentUser.getFullName());
        System.out.println("""
                ═════════════════════════════════════════════════════════════════════════════════
                │                                                                               │
                │  [1] 📈  Se aktiemarked og aktuelle kurser                                    │
                │                                                                               │
                │  [2] 💰  Køb aktier                                                           │
                │                                                                               │
                │  [3] 💸  Sælg aktier                                                          │
                │                                                                               │
                │  [4] 📂  Se min portefølje                                                    │
                │                                                                               │
                │  [5] 📜  Se transaktionshistorik                                              │
                │                                                                               │
                │  [6] 🚪  Log ud                                                               │
                │                                                                               │
                │  [7] ❌  Luk programmet                                                       │
                │                                                                               │
                ═════════════════════════════════════════════════════════════════════════════════
                """);
        System.out.print("Vælg venligst en mulighed (1-7): ");
    }

    public static void displayAdminMenu() {
        System.out.println();
        System.out.println("""
                ═════════════════════════════════════════════════════════════════════════════════
                                                   ADMIN MENU\s
                ═════════════════════════════════════════════════════════════════════════════════
                │                                                                               │
                │  [1] 📊  Se oversigt over alle brugeres porteføljer                           │
                │                                                                               │
                │  [2] 🏆  Vis rangliste over brugere                                           │
                │                                                                               │
                │  [3] 📈  Se fordeling på aktier og sektorer                                   │
                │                                                                               │
                │  [4] 🚪  Log ud                                                               │
                │                                                                               │
                │  [5] ❌  Luk programmet                                                       │
                │                                                                               │
                ═════════════════════════════════════════════════════════════════════════════════
                """);
        System.out.print("Vælg venligst en mulighed (1-5): ");
    }

    public static void displayStockMarket() {
        System.out.println();
        System.out.println("""
                ════════════════════════════════════════════════════════════════════════════════════════════
                                                         AKTIEMARKED\s
                ════════════════════════════════════════════════════════════════════════════════════════════""");
        System.out.printf("│ %-25s │ %-10s │ %-14s │ %-20s │ %-7s │%n",
                "Aktie Navn", "Ticker", "Pris", "Sektor", "Rating");
        System.out.println("├───────────────────────────┼────────────┼────────────────┼──────────────────────┼─────────┤");

        for (Stock s : stocksList) {
            System.out.printf(Locale.GERMANY, "│ %-25s │ %-10s │ %,10.2f %-3s │ %-20s │ %-7s │%n",
                    s.getName().length() > 25 ? s.getName().substring(0, 22) + "..." : s.getName(),
                    s.getTicker(),
                    s.getPrice(),
                    s.getCurrency(),
                    s.getSector(),
                    s.getRating());
        }
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════\n\n");
    }

    //printer transactions ud for medlem logget ind
    public static void printTransactionHistory(ArrayList<Transaction> transactions) {

        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("                  DIN TRANSAKTIONSHISTORIK                  ");
        System.out.println("════════════════════════════════════════════════════════════\n");
        System.out.printf("%-7s %-9s %-8s %-10s %-7s %-10s%n", "TICKER", "PRIS", "VALUTA", "KØB/SALG", "ANTAL", "DATO");
        System.out.println("────────────────────────────────────────────────────────────");

        for (Transaction t : transactions) {

            if (t.getUserID() == getCurrentUserID()) {
                System.out.printf("%-7s %-,9.2f %-8s %-10s %-7d %s%n",
                        t.getTicker(),
                        t.getPrice(),
                        t.getCurrency(),
                        t.getOrderType(),
                        t.getBoughtShares(),
                        t.getDate());
            }
        }
        System.out.println("\n\n");
    }

//    // ==================== HEADERS ====================
//
    public static void printHeader(String title) {
        System.out.println(DOUBLE_LINE);
        System.out.printf("%" + ((TABLE_WIDTH + title.length()) / 2) + "s%n", title);
        System.out.println(DOUBLE_LINE);
    }

    public static void printBuyHeader() {
        printHeader(/*"💰 " + */"KØB AKTIER");
    }

    public static void printSellHeader() {
        printHeader(/*"💸 " + */"SÆLG AKTIER");
    }

    public static void printBuySummary(Stock stock, int shares, double totalPrice, double currentBalance) {
        System.out.println("\n┌─────────────────────── KØB OPSUMMERING ───────────────────────┐");
        System.out.printf("│ Aktie:           %-45s │%n", stock.getName());
        System.out.printf("│ Ticker:          %-45s │%n", stock.getTicker());
        System.out.printf(Locale.GERMANY, "│ Antal aktier:    %-45d │%n", shares);
        System.out.printf(Locale.GERMANY, "│ Pris pr. aktie:  %,-45.2f DKK │%n", stock.getPrice());
        System.out.printf(Locale.GERMANY, "│ Total pris:      %,-45.2f DKK │%n", totalPrice);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        System.out.printf(Locale.GERMANY, "│ Nuværende saldo: %,-45.2f DKK │%n", currentBalance);
        System.out.printf(Locale.GERMANY, "│ Ny saldo:        %,-45.2f DKK │%n", (currentBalance - totalPrice));
        System.out.println("└────────────────────────────────────────────────────────────────┘\n");
    }

    public static void printSellSummary(Stock stock, int shares, double totalPrice, double currentBalance) {
        System.out.println("\n┌─────────────────────── SALG OPSUMMERING ───────────────────────┐");
        System.out.printf("│ Aktie:           %-45s │%n", stock.getName());
        System.out.printf("│ Ticker:          %-45s │%n", stock.getTicker());
        System.out.printf(Locale.GERMANY, "│ Antal aktier:    %-45d │%n", shares);
        System.out.printf(Locale.GERMANY, "│ Pris pr. aktie:  %,-45.2f DKK │%n", stock.getPrice());
        System.out.printf(Locale.GERMANY, "│ Total salgspris: %,-45.2f DKK │%n", totalPrice);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        System.out.printf(Locale.GERMANY, "│ Nuværende saldo: %,-45.2f DKK │%n", currentBalance);
        System.out.printf(Locale.GERMANY, "│ Ny saldo:        %,-45.2f DKK │%n", (currentBalance + totalPrice));
        System.out.println("└────────────────────────────────────────────────────────────────┘\n");
    }

    public static void waitForEnter() {
        System.out.println("\nTryk Enter for at fortsætte...");
        try {
            System.in.read();

            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
        }
    }

    public static void printDoubleLine() {
        System.out.println(DOUBLE_LINE);
    }

    public static void printSingleLine() {
        System.out.println(SINGLE_LINE);
    }

    public static void printBlankLine() {
        System.out.println();
    }

//    // ==================== SUCCESS/ERROR MESSAGES ====================
//
//    public static void printSuccess(String message) {
//        System.out.println("\n✅ " + message);
//    }
//
//    public static void printError(String message) {
//        System.out.println("\n❌ " + message);
//    }
//
//    public static void printInfo(String message) {
//        System.out.println("   " + message);
//    }
//
//    public static void printBuySuccess(int shares, String ticker, double newBalance) {
//        printSuccess("Køb gennemført!");
//        printInfo(String.format("Du har købt %d aktier af %s", shares, ticker));
//        printInfo(String.format(Locale.GERMANY, "Ny kontantbeholdning: %,.2f DKK", newBalance));
//    }
//
//    public static void printSellSuccess(int shares, String ticker, double newBalance) {
//        printSuccess("Salg gennemført!");
//        printInfo(String.format("Du har solgt %d aktier af %s", shares, ticker));
//        printInfo(String.format(Locale.GERMANY, "Ny kontantbeholdning: %,.2f DKK", newBalance));
//    }
//
//    public static void printInsufficientFunds(double balance, double required) {
//        printError("Du har ikke nok penge til denne transaktion.");
//        printInfo(String.format(Locale.GERMANY, "Kontantbeholdning: %,.2f DKK", balance));
//        printInfo(String.format(Locale.GERMANY, "Mangler:            %,.2f DKK", (required - balance)));
//    }
//
//    public static void printCancelled(String action) {
//        System.out.println("\n❌ " + action + " annulleret");
//    }
//
//    // ==================== LOGIN MESSAGES ====================
//
//    public static void printLoginSuccess(String name) {
//        System.out.println("\n✅ Logget ind som: " + name + "\n");
//    }
//
//    public static void printLogoutMessage() {
//        System.out.println("\n🚪 Logger ud...");
//        System.out.println("✅ Du er nu logget ud.\n");
//    }
//
//    public static void printShutdownMessage() {
//        System.out.println("\n🚪 Lukker ned...");
//    }
//
//    // ==================== INPUT PROMPTS ====================
//
//    public static void printTickerPrompt() {
//        System.out.print("\n📊 Indtast ticker på den aktie du vil købe: ");
//    }
//
//    public static void printSharesPrompt() {
//        System.out.print("Hvor mange aktier vil du købe?: ");
//    }
//
//    public static void printConfirmationPrompt(String action) {
//        System.out.print("✅ Vil du bekræfte " + action + "? (ja/nej): ");
//    }
//
//    public static void printEmailPrompt() {
//        System.out.print("\n📧 Indtast venligst din email for at logge ind: ");
//    }
//
//    public static void printUsernamePrompt() {
//        System.out.print("\n👤 Indtast admin brugernavn: ");
//    }
//
//    public static void printPasswordPrompt() {
//        System.out.print("🔑 Indtast venligst admin password: ");
//    }

}
