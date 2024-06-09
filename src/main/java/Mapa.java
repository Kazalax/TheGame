import java.util.Random;
import java.util.Scanner;

public class Mapa {
    private static final int sirka = 11;
    private static final int vyska = 7;
    private final char[][] mapa;
    private int hracX = sirka;
    private int hracY = vyska;

    private static final char PUSTINA = '\u25A2';
    private static final char HRAC = '\u25A3';
    private static final char STENA = '\u25A9';
    private static final char ODHALENO = '\u25A7';
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    Hra hra = new Hra();
    private final Hrdina hrdina;


    /**
     * konstruktor Mapy
     */
    public Mapa(Hrdina hrdina) {
        this.hrdina = hrdina;
        mapa = new char[vyska][sirka];
        inicializujMapu();
    }

    /**
     * inicializuje Mapy
     */
    private void inicializujMapu() {
        for (int y = 0; y < vyska; y++) {
            for (int x = 0; x < sirka; x++) {
                if (x == 0 || x == sirka - 1 || y == 0 || y == vyska - 1) {
                    mapa[y][x] = STENA;
                } else {
                    mapa[y][x] = PUSTINA;
                }
            }
        }
    }

    /**
     * umistí hráče na mapu
     */
    public void umistiHrace() {
        hracX = sirka / 2;
        hracY = vyska / 2;
        mapa[hracY][hracX] = HRAC;
    }

    private boolean jeVHracimPoli(int x, int y) {
        return x >= 0 && x < sirka && y >= 0 && y < vyska && mapa[y][x] != STENA;
    }


    /**
     * umožňuje pohyb hráče po mapě
     */
    public void PohniHracem() {
        boolean spustecBoje = false;//pokud je true spusti souboj

        while (!spustecBoje) {
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
            switch (volba) {
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
                // po vstoupeni do pustiny je 20% šance na souboj
                if (mapa[newY][newX] == PUSTINA && random.nextInt(5) == 0) {
                    spustecBoje = true;
                    hra.souboj(hrdina);
                }

                mapa[hracY][hracX] = ODHALENO; // aktualní pozici změní na odhaleno
                hracX = newX;
                hracY = newY;
                mapa[hracY][hracX] = HRAC; // přesune hráče
            } else {
                System.out.println("Nemůžeš se pohybovat mimo mapu!");
            }
        }
    }
}

