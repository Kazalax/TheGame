import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Hra {
    Random random = new Random();

    public Nepritel vyberNepritele() {
        Nepritel[] nepratele = {new UcitelMatematiky(), new StinnyStrom(), new Bezdomovec()};
        int nahodnyIndex = random.nextInt(nepratele.length);
        return nepratele[nahodnyIndex];
    }

    public void souboj(Hrdina hrdina) {
        Scanner scanner = new Scanner(System.in);
        Nepritel nepritel = vyberNepritele();

        System.out.println("Zaútočil na tebe " + nepritel.jmeno + "!");
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

