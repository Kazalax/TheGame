import java.util.Random;
import java.util.Scanner;

public class Hra {
    Random random = new Random();


    /**
     * spustí souboj hrdiny s nepřítelem
     */
    public void souboj(Hrdina hrdina, Hrdina nepritel) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Zaútočil na tebe " + nepritel.jmeno + "!");
        while (hrdina.jeNazivu() && nepritel.jeNazivu()) {
            hrdina.dramatickaPauza();
            if (hrdina.omraceni) {
                System.out.println("Byl jsi omráčen ztrácíš tah!");
                hrdina.zkontrolujOmraceni();
            } else {
                System.out.println("\n--- Nový tah ---");
                System.out.println("Vyber útok:");
                System.out.println("1. Primární útok");
                System.out.println("2. Sekundární útok");
                System.out.println("3. Speciální útok");
                System.out.println("4. Popis schopností");

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
                    case 4:
                        System.out.println(hrdina.popisSchopnosti());
                        continue;
                    default:
                        System.out.println("Neplatná volba, prosím vyber znovu.");
                        continue;
                }
            }


            //regeneruje manu hrdiny i nepřítele
            hrdina.regenerujManu(10);
            nepritel.regenerujManu(10);
            //zkonroluje negativní efekty nepřítele

            //pauza 1 s
            hrdina.dramatickaPauza();

            if (nepritel.jeNazivu()) {
                // NPC Nepřítel náhodně útočí
                if (nepritel.omraceni) {
                    System.out.println("nepřítel byl omráčen!");
                    nepritel.zkontrolujOmraceni();
                } else {
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
                }
            }
            hrdina.zkontrolujEfekty();
            nepritel.zkontrolujEfekty();
            // Dramatická pauza
            hrdina.dramatickaPauza();

            System.out.println("\n--- Statusy ---");
            System.out.println(hrdina.status());
            System.out.println(nepritel.status());

            if (!hrdina.jeNazivu()) {
                System.out.println(hrdina.jmeno + " byl poražen.");
            }

            if (!nepritel.jeNazivu()) {
                System.out.println(nepritel.jmeno + " byl poražen.");
            }

        }
    }
}

