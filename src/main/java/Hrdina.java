import java.util.Random;

abstract class Hrdina {
    public String trida;
    public String jmeno;
    protected int zivoty;
    protected int maxZivoty;
    protected int sila;
    protected int mana;
    protected int maxMana;
    protected boolean ochrana = false; // Indikuje, zda je hrdina pod ochranou
    protected int ochranaTahy = 0; // Počet tahů, po které ochrana trvá
    protected boolean otrava = false; // Indikuje, zda je hrdina pod otravu
    protected int otravaTahy = 0; // Počet tahů, po které otrava trvá
    protected boolean omraceni = false; // Indikuje, zda je hrdina pod omráčením
    protected int omraceniTahy = 0; // Počet tahů, po které omráčení trvá

    /**
     * konstruktor třídy hrdina
     *
     * @param jmeno     jméno které zadává uživatel
     * @param trida     třída kterou si vybere uživatel ze seznamu hrdinů
     * @param zivoty    hodnota poškození kterou je postava schopna vydřzet
     * @param maxZivoty maximální hodnota poškození kterou je postava schopna vydřzet
     * @param sila      poškození které davá opnentovi
     * @param mana      hodnota kolik má postava many pro seslaní specialního útoku
     * @param maxMana   maximální hodnota hodnota kolik má postava many pro seslaní specialního útoku
     */
    public Hrdina(String jmeno, String trida, int zivoty, int maxZivoty, int sila, int mana, int maxMana) {
        this.trida = trida;
        this.jmeno = jmeno;
        this.zivoty = zivoty;
        this.sila = sila;
        this.mana = mana;
        this.maxZivoty = maxZivoty;
        this.maxMana = maxMana;
    }

    /**
     * @return kontroju zda je postava naživu
     */
    public boolean jeNazivu() {
        return this.zivoty > 0;
    }


    Random random = new Random();

    /**
     * aktivuje ochranu
     */
    public void aktivujOchranu(int tahy) {
        this.ochrana = true;
        this.ochranaTahy = tahy;
    }

    /**
     * aktivuje otravu
     */
    public void aktivujOtravu(int tahy) {
        this.otrava = true;
        this.otravaTahy = tahy;
    }

    /**
     * aktivuje omráčení
     */
    public void aktivujOmraceni(int tahy) {
        this.omraceni = true;
        this.omraceniTahy = tahy;
    }

    /**
     * zkontroluje negativní efekty
     */
    public void zkontrolujEfekty() {
        if (ochrana) {
            ochranaTahy--;
            if (ochranaTahy <= 0) {
                ochrana = false;
            }
        }

        if (otrava) {
            otravaTahy--;
            if (otravaTahy <= 0) {
                otrava = false;
            }
        }

        if (omraceni) {
            omraceniTahy--;
            if (omraceniTahy <= 0) {
                omraceni = false;
            }
        }
    }

    /**
     * metoda pro vypisování popisků k schopnostem
     */
    public abstract String popisSchopnosti();


    /**
     * Metoda pro přijmutí útoku
     */

    public void prijmoutUtok(int uder) {
        dramatickaPauza();
        if (ochrana) {
            System.out.println(jmeno + " je chráněn a neobdržel žádné poškození.");
            ochrana = false;
        } else {
            if (uder > 0) {
                this.zivoty -= uder;
                System.out.println(jmeno + " byl zasažen! Ztratil " + uder + " životů."); // Zobrazení typu útoku
            }
            if (otrava) {
                this.zivoty -= 10;
                System.out.println(jmeno + " má otravu a ztratil 10 životů.");
            }
        }
        if (zivoty < 0) {
            this.zivoty = 0;
        }
    }

    /**
     * regeneruje určité množství many
     *
     * @param mnozstvi množství vyregenerované many
     */
    public void regenerujManu(int mnozstvi) {
        this.mana = Math.min(this.mana + mnozstvi, this.maxMana);
    }

    /**
     * udělá pauzu na 1 sekundu pro dramatičnost
     */
    public void dramatickaPauza() {
        try {
            Thread.sleep(1000); // 1 sekunda pauza
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * definice pro metody útoků
     */
    public abstract void utokPrimarni(Hrdina nepritel);

    public abstract void utokSekundarni(Hrdina nepritel);

    public abstract void utokSpecialni(Hrdina nepritel);

    /**
     * vypíše aktualní staty
     *
     * @return vrací jmeno, zivoty, maxZivoty, mana, maxMana
     */
    public String status() {
        return (String.format("%s \n životy: %s/%s \n mana: %s/%s", jmeno, zivoty, maxZivoty, mana, maxMana));
    }

}

/**
 * třídy hrdinů, které si uživatel vybírá
 */
class Zednik extends Hrdina {
    public Zednik(String jmeno) {
        super(jmeno, "Zedník", 150, 150, 25, 30, 30);
    }

    @Override
    public void utokPrimarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " používá: útok zednickým kladivem a způsobuje " + uder + " poškození!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSekundarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " používá: hod cihlou a způsobuje " + uder + " poškození!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSpecialni(Hrdina nepritel) {
        if (mana >= 30) {
            System.out.println(jmeno + " používá speciální útok: Opření o lopatu!");
            mana = mana - 30;
            aktivujOchranu(2);
        } else {
            System.out.println("Nedostatek many!");
        }

    }

    @Override
    public String popisSchopnosti() {
        return "Zedník má následující schopnosti:\n" +
                "1. Útok zednickým kladivem: Silný útok blízkého dosahu.\n" +
                "2. Hod cihlou: Středně silný útok na dálku.\n" +
                "3. Opření o lopatu: Speciální útok, který aktivuje ochranu na 2 tahy.";

    }
}

class Knez extends Hrdina {
    public Knez(String jmeno) {
        super(jmeno, "Kněz", 80, 80, 20, 60, 60);
    }

    @Override
    public void utokPrimarni(Hrdina nepritel) {
        int uder = (sila + random.nextInt(10)) / 2;
        System.out.println(jmeno + " používá: Exorcismus a způsobuje " + uder + " poškození a vyléčil si " + uder + " životů!");
        nepritel.prijmoutUtok(uder);
        zivoty = zivoty + uder;
        if (zivoty > maxZivoty) {
            zivoty = maxZivoty;
        }
    }

    @Override
    public void utokSekundarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(15);
        System.out.println(jmeno + " používá: Upálení v Kosnici a způsobuje " + uder + " poškození!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSpecialni(Hrdina nepritel) {
        if (mana >= 30) {
            System.out.println(jmeno + " používá speciální útok: Božský úder!");
            mana = mana - 30;
            nepritel.aktivujOmraceni(2);
        } else {
            System.out.println("Nedostatek many!");
        }

    }

    @Override
    public String popisSchopnosti() {
        return "Kněz má následující schopnosti:\n" +
                "1. Exorcismus: Slabý útok, který vyléčí stejné množství životů jako způsobeného poškození nepříteli.\n" +
                "2. Upálení v Kostnici: S pomocí silou boží uvrhne nepřítele do plamenů a způsobí vysoké poškození.\n" +
                "3. Božský úder: Speciální útok, který aktivuje omráČení na nepřítele na 2 tahy.";


    }
}

class Cisnik extends Hrdina {
    public Cisnik(String jmeno) {
        super(jmeno, "Číšník", 100, 100, 30, 30, 30);
    }

    @Override
    public void utokPrimarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " používá: hod tácem a způsobuje " + uder + " poškození!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSekundarni(Hrdina nepritel) {
        int uder = 0;
        System.out.println(jmeno + " používá: Další runda a způsobuje " + uder + " poškození!");
        nepritel.aktivujOtravu(3);
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSpecialni(Hrdina nepritel) {
        if (mana >= 30) {
            int uder = sila + random.nextInt(25);
            System.out.println(jmeno + " používá speciální útok: Smršť nádobí!");
            mana = mana - 30;
            nepritel.prijmoutUtok(uder);
        } else {
            System.out.println("Nedostatek many!");
        }

    }

    @Override
    public String popisSchopnosti() {
        return "Kněz má následující schopnosti:\n" +
                "1. Hod tácem: Středně silní útok na delší vzádlenost\n" +
                "2. Další runda: Způsobuje nepříteli otravu alkoholem\n" +
                "3. Smršť nádobí: Hodí po nepříteli hromadu špinavého nádobí a způsobí vysoké poškození ";


    }

}


