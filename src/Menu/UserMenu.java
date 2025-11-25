package Menu;

public class UserMenu {

    public void printUserMainMenu() {
        System.out.println();
        System.out.println("""
                           Velkommen til brugermenuen!
                ┌──────────────────────────────────────────────────┐
                │ Tryk  1 for at se aktiemarked og aktuel kurs.    │
                │ Tryk  2 for at registrere køb af aktie.          │
                │ Tryk  3 for at se portofølje.                    │
                │ Tryk  4 for at se transaktionshistorik.          │
                └──────────────────────────────────────────────────┘
                """);
    }
}
