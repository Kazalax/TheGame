import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hra {
    public void souboj(){
    Scanner scanner = new Scanner(System.in);

        // Seznam dostupných hrdinů
        String[] hrdinové = {"Zedník", "Kněz", "Číšník"};

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
            return;
        }



        // Vytvoření instance vybraného hrdiny
        Hrdina hrdina;
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


    Nepritel nepritel = new Nepritel("Bezdomovec");

        while (hrdina.jeNazivu() && nepritel.jeNazivu()) {
            hrdina.dramatickaPauza();
            if (hrdina.omraceni){
                System.out.println("Byl jsi omráčen ztrácíš tah!");
            } else {
                    System.out.println("\n--- Nový tah ---");
                    System.out.println("Vyber útok:");
                    System.out.println("1. Primární útok");
                    System.out.println("2. Sekundární útok");
                    System.out.println("3. Speciální útok");

                    int volba = scanner.nextInt();

                    switch (volba) {
                        case 1:
                            hrdina.utokPrimarni(nepritel);
                            break;
                        case 2:
                            hrdina.utokSekundarni(nepritel);
                            break;
                        case 3:
                            hrdina.utokSpecialni(nepritel);
                            break;
                        default:
                            System.out.println("Neplatná volba, prosím vyber znovu.");
                            continue;

                }
            }


        // Regenerace many hrdiny a nepřítele
            hrdina.regenerujManu(10);
            nepritel.regenerujManu(10);


            hrdina.zkontrolujEfekty();
            nepritel.zkontrolujEfekty();
        // Dramatická pauza
            hrdina.dramatickaPauza();

        // NPC Nepřítel náhodně útočí
        if(nepritel.omraceni) {
            System.out.println("nepřítel byl omráčen!");
        } else{
        int npcVolba = hrdina.random.nextInt(3) + 1;
        switch (npcVolba) {
            case 1:
                nepritel.utokPrimarni(hrdina);
                break;
            case 2:
                nepritel.utokSekundarni(hrdina);
                break;
            case 3:
                nepritel.utokSpecialni(hrdina);
                break;
        }

        // Dramatická pauza
        nepritel.dramatickaPauza();

        System.out.println("\n--- Statusy ---");
        System.out.println(hrdina.status());
        System.out.println(nepritel.status());

        hrdina.dramatickaPauza();

        if (!hrdina.jeNazivu()) {
            System.out.println(hrdina.jmeno + " byl poražen.");
        }

        if (!nepritel.jeNazivu()) {
            System.out.println(nepritel.jmeno + " byl poražen.");
        }

            }
                }

        scanner.close();
    }
}

