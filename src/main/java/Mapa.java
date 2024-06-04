import java.util.*;

public class Mapa {
    private static final int sirka = 11;
    private static final int vyska = 7;
    private char[][] mapa;
    private int hracX = sirka;
    private int hracY = vyska;

    private static final char PUSTINA = '\u25A2';
    private static final char HRAC = '\u25A3';
    private static final char STENA = '\u25A9';
    private static final char ODHALENO = '\u25A7';
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    Hra hra = new Hra();
    private Hrdina hrdina;

    public Mapa(Hrdina hrdina) {
        this.hrdina = hrdina;
        mapa = new char[vyska][sirka];
        inicializujMapu();
    }

    private void inicializujMapu() {
        for (int y = 0; y < vyska; y++) {
            for (int x = 0; x < sirka; x++) {
                if (x == 0 || x == sirka - 1 || y == 0 || y == vyska - 1) {
                    mapa[y][x] = STENA; // walls
                } else {
                    mapa[y][x] = PUSTINA; // empty space
                }
            }
        }
    }

    public void umistiHrace() {
        hracX = sirka / 2;
        hracY = vyska / 2;
        mapa[hracY][hracX] = HRAC;
    }
    private boolean jeVHracimPoli(int x, int y) {
        return x >= 0 && x < sirka && y >= 0 && y < vyska;
    }

    public void PohniHracem() {
        boolean combatTriggered = false; // Flag to track combat status

        while (!combatTriggered) {
            //ukaze mapu
            for (int y = 0; y < vyska; y++) {
                for (int x = 0; x < sirka; x++) {
                    System.out.print(mapa[y][x]);
                }
                System.out.println();
            }
            int newX = hracX;
            int newY = hracY;

            System.out.print("Vyber si kam chceš jít!(W-nahoru, S-dolů, A-Doleva, D-Doprava)");
            String volba = scanner.nextLine().toLowerCase();
            switch (volba){
                case "w":
                    newY = hracY - 1;
                    break;
                case "a":
                    newX = hracX - 1;
                    break;
                case "s":
                    newY = hracY + 1;
                    break;
                case "d":
                    newX = hracX + 1;
                    break;
                default:
                    System.out.println("Neplatná volba, zkuste to znovu.");
                    continue; // Skip this iteration if invalid choice
            }

            if (jeVHracimPoli(newX, newY)) {
                // Combat check before updating the player's position
                if (mapa[newY][newX] == PUSTINA && random.nextInt(5) == 0) {
                    combatTriggered = true; // Set flag if combat triggers
                    hra.souboj(hrdina);
                }

                mapa[hracY][hracX] = ODHALENO; // Reveal current position
                hracX = newX;
                hracY = newY;
                mapa[hracY][hracX] = HRAC; // Update player position
            } else {
                System.out.println("Nemůžeš se pohybovat mimo mapu!");
            }
        }
    }
}

