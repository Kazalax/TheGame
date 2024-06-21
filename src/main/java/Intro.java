import java.util.Scanner;

public class Intro {


    Scanner scanner = new Scanner(System.in);
    public void uvod(){
        System.out.println("Vítejte, odvážný hrdino, ve světě zvaném Tajemný Les. Tento kdysi klidný a mírumilovný kraj byl zasažen temnými silami, které vnesly strach a chaos do života jeho obyvatel. Temné stíny, kouzelní tvorové a neznámí nepřátelé nyní obývají lesní hloubky a čekají na neopatrné dobrodruhy.\n" +
                "Jste jeden z mála, kteří mají odvahu postavit se těmto hrozbám. Jako vybraný hrdina máte jedinečnou možnost vrátit mír a harmonii do Tajemného Lesa. Vaše cesta bude plná nebezpečí a nástrah, ale také příležitostí prokázat svou sílu, odvahu a důvtip.\n" +
                "Na začátku své cesty si vyberete svou roli – jste zdatný Zedník, který svou silou dokáže rozdrtit i ten nejsilnější kámen? Nebo snad moudrý Kněz, který ovládá Božské síly a kouzla, jež mohou zvrátit průběh bitvy? Nebo jste rychlý a obratný Číšník, který svou mrštností a hbitostí dokáže překvapit každého nepřítele?\n" +
                "Vydejte se na cestu plnou soubojů, tajemství a objevování. Prozkoumávejte temná zákoutí lesa, bojujte s nepřáteli, odhalujte skrytá tajemství a dokažte, že právě vy jste tím pravým hrdinou, který může přinést světlo do temnoty.\n" +
                "Osud Tajemného Lesa leží ve vašich rukou. Podaří se vám přemoci temnotu a vrátit klid do této země? Vydejte se na dobrodružství svého života a staňte se legendou!");
    }
    // Seznam dostupných hrdinů
    String[] hrdinové = {"Zedník", "Kněz", "Číšník"};

    public Hrdina vyberHrdinu() {
        System.out.println("Vyber jméno hrdiny:");
        String jmeno = scanner.nextLine();

        // Zobrazení seznamu hrdinů
        System.out.println("Vyberte si hrdinu:");
        for (int i = 0; i < hrdinové.length; i++) {
            System.out.println((i + 1) + ". " + hrdinové[i]);
        }

        // Získání volby uživatele
        int volbaHrdiny = scanner.nextInt();
        if (volbaHrdiny < 1 || volbaHrdiny > hrdinové.length) {
            System.out.println("Neplatná volba. Zvolte číslo z nabídky.");
            return null;
        }
        Hrdina hrdina;

        // Vytvoření instance vybraného hrdiny
        switch (volbaHrdiny) {
            case 1:
                hrdina = new Zednik(jmeno);
                break;
            case 2:
                hrdina = new Knez(jmeno);
                break;
            case 3:
                hrdina = new Cisnik(jmeno);
                break;
            default:
                hrdina = null; // V případě chyby
                break;
        }
        return hrdina;
    }
}
