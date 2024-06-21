import java.util.Random;
import java.util.Scanner;

public class Mapa {
    private static final int sirka = 11;
    private static final int vyska = 7;

    private final char[][] mapa;

    private int hracX = sirka;
    private int hracY = vyska;

    private static final char NEPRITEL = '\u25A4';
    private static final char PUSTINA = '\u25A2';
    private static final char HRAC = '\u25A3';
    private static final char STENA = '\u25A9';
    private static final char ODHALENO = '\u25A7';
    private final Nepritel[] nepratele = new Nepritel[3];
    private final int[][] poziceNepratel = new int[3][2];
    private final boolean[] nepratelePorazeni = new boolean[3];

    private final Hrdina hrdina;

    Scanner scanner = new Scanner(System.in);
    Hra hra = new Hra();


    /**
     * konstruktor Mapy
     */
    public Mapa(Hrdina hrdina) {
        this.hrdina = hrdina;
        mapa = new char[vyska][sirka];
        inicializujMapu();
        umistiNepratele();
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
     * Vyčistí obrazovku před tiskem nové mapy
     */
    private void vycistiObrazovku() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * umístí hráče na mapu
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
     * umístí nepřátele na mapu
     */
    private void umistiNepratele() {
        nepratele[0] = new UcitelMatematiky();
        nepratele[1] = new StinnyStrom();
        nepratele[2] = new Bezdomovec();

        poziceNepratel[0][0] = 2; // x pozice prvního nepřítele
        poziceNepratel[0][1] = 2; // y pozice prvního nepřítele

        poziceNepratel[1][0] = 4; // x pozice druhého nepřítele
        poziceNepratel[1][1] = 4; // y pozice druhého nepřítele

        poziceNepratel[2][0] = 8; // x pozice třetího nepřítele
        poziceNepratel[2][1] = 5; // y pozice třetího nepřítele

        for (int i = 0; i < nepratele.length; i++) {
            mapa[poziceNepratel[i][1]][poziceNepratel[i][0]] = NEPRITEL;
        }
    }


    /**
     * umožňuje pohyb hráče po mapě
     */
    public void PohniHracem() {
        while (true) {
            vycistiObrazovku();
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
                boolean soubojSpusten = false;
                for (int i = 0; i < nepratele.length; i++) {
                    if (newX == poziceNepratel[i][0] && newY == poziceNepratel[i][1] && !nepratelePorazeni[i]) {
                        hra.souboj(hrdina, nepratele[i]);
                        nepratelePorazeni[i] = true;
                        soubojSpusten = true;
                        break;
                    }
                }

                if (!soubojSpusten) {
                    mapa[hracY][hracX] = ODHALENO; // Aktualní pozici změní na odhaleno
                    hracX = newX;
                    hracY = newY;
                    mapa[hracY][hracX] = HRAC; // Přesune hráče
                }

                // Kontrola, zda jsou všichni nepřátelé poraženi
                boolean vsichniNepratelePorazeni = true;
                for (boolean porazen : nepratelePorazeni) {
                    if (!porazen) {
                        vsichniNepratelePorazeni = false;
                        break;
                    }
                }

            if (vsichniNepratelePorazeni) {
                System.out.println("Po dlouhých a vyčerpávajících bojích se vám podařilo porazit všechny nepřátele, kteří přinášeli temnotu a strach do Tajemného Lesa. Vaše odvaha, dovednosti a odhodlání vám umožnily postavit se proti Učiteli Matematiky, Stinnému Stromu a Králi odpadků. Každý z těchto nepřátel padl pod vaší silou a prozíravostí.\n" +
                        "Jak poslední nepřítel padl k zemi, les se naplnil tichým šepotem úlevy. Stromy, které byly po dlouhá léta svědky temných událostí, začaly znovu rozkvétat a ptáci se vrátili zpět do svých hnízd. Světlo proniklo do nejhlubších zákoutí lesa, které byly dlouho zahaleny stíny.\n" +
                        "Obyvatelé Tajemného Lesa vyšli ze svých úkrytů a s úžasem pozorovali, jak se jejich domov vrací k životu. Vesničané se shromáždili kolem vás, hrdiny, který jim vrátil naději a bezpečí. Vaše jméno se bude vyprávět v příbězích a písních po mnoho generací.\n" +
                        "Gratulujeme!");

                    try {
                        Thread.sleep(10000); // 10 sekunda pauza
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                System.out.println("\nKonec hry \n" +
                        "\nDěkujeme, že jste hráli naši hru. Doufáme, že jste si užili toto dobrodružství a těšíme se na vaše další hrdinské činy!");
                break;
            }else {
                System.out.println("Vracíte se na mapu...");
                }
            }
        }
    }
}

