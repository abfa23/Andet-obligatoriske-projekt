package Menu;

public class AdminMenu {

    public void printMainMenu() {
        System.out.println();
        System.out.println("""
                           Velkommen til admin menuen!
                ┌────────────────────────────────────────────────────────────────┐
                │ Tryk  1 for at se en oversigt over brugernes portoføljeværdier.│
                │ Tryk  2 for at få en rangliste over brugerne.                  │
                │ Tryk  3 for at få vist en fordelinger på aktier og sektorer.   │
                └────────────────────────────────────────────────────────────────┘
                """);
    }
}
