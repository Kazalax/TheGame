import java.util.Scanner;

public class Intro {


    Scanner scanner = new Scanner(System.in);

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
