package InvestmentClub;

import Entities.User;
import Entities.Stock;

import java.util.Locale;

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
}
