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

    public Hrdina(String trida,String jmeno, int zivoty,int maxZivoty, int sila, int mana,int maxMana){
        this.trida = trida;
        this.jmeno = jmeno;
        this.zivoty = zivoty;
        this.sila = sila;
        this.mana = mana;
        this.maxZivoty = maxZivoty;
        this.maxMana = maxMana;
    }


    public String getTrida() {
        return trida;
    }

    public String getJmeno() {
        return jmeno;
    }

    public boolean jeNazivu() {
        return this.zivoty > 0;
    }

    Random random = new Random();

    //Ochrana
    public void aktivujOchranu(int tahy){
        this.ochrana = true;
        this.ochranaTahy = tahy;
    }
    //otrava
    public void aktivujOtravu(int tahy){
        this.otrava = true;
        this.otravaTahy = tahy;
    }
    public void aktivujOmraceni(int tahy){
        this.omraceni = true;
        this.omraceniTahy = tahy;
    }

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





    // Metoda pro přijmutí útoku
    public void prijmoutUtok(int uder) {
        dramatickaPauza();
        if (ochrana) {
            System.out.println(jmeno + " je chráněn a neobdržel žádné poškození.");
            ochrana = false;
        } else if (otrava) {
            this.zivoty -= 10;
            System.out.println(jmeno + " má otravu alkoholem a ztratil 10 životů.");
        }
        if (uder > 0) {
            this.zivoty -= uder;
            System.out.println(jmeno + " byl zasažen! Ztratil " + uder + " životů."); // Zobrazení typu útoku
        }

    }

    //regenrace many
    public void regenerujManu(int mnozstvi) {
        this.mana = Math.min(this.mana + mnozstvi, this.maxMana);
    }

    //pauza 1s
    public void dramatickaPauza() {
        try {
            Thread.sleep(1000); // 1 sekunda pauza
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //utoky
    public abstract void utokPrimarni(Hrdina nepritel);
    public abstract void utokSekundarni(Hrdina nepritel);
    public abstract void utokSpecialni(Hrdina nepritel);

    //vypise aktualni staty
    public String status(){
        return (String.format( "%s \n životy: %s/%s \n mana: %s/%s", jmeno, zivoty,maxZivoty,mana,maxMana));
    }

}

class Zednik extends Hrdina{
    public Zednik(String jmeno) {
        super("Zedník",jmeno, 150,150,25,30,30);
    }

    @Override
    public void utokPrimarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " používá: útok zednickým kladivem!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSekundarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " používá: hod cihlou!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSpecialni(Hrdina nepritel) {
        if (mana>=30){
            System.out.println(jmeno + " používá speciální útok: Opření o lopatu!");
            mana = mana - 30;
            aktivujOchranu(2);
        } else {
            System.out.println("Nedostatek many!");
        }

    }

}


class Knez extends Hrdina{
    public Knez(String jmeno) {
        super("Kněz",jmeno, 80,80,20,60,60);
    }

    @Override
    public void utokPrimarni(Hrdina nepritel) {
        int uder = (sila + random.nextInt(10))/2;
        System.out.println(jmeno + " používá: Exorcismus!");
        nepritel.prijmoutUtok(uder);
        zivoty = zivoty + uder;
        if (zivoty>maxZivoty){
            zivoty = maxZivoty;
        }
    }

    @Override
    public void utokSekundarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " používá: Upálení v Kosnici!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSpecialni(Hrdina nepritel) {
        if (mana>=30){
            int uder = sila + random.nextInt(10);
            System.out.println(jmeno + " používá speciální útok: Svěcená Moč!");
            mana = mana - 30;
            nepritel.aktivujOmraceni(2);
        } else {
            System.out.println("Nedostatek many!");
        }

    }


}
class Cisnik extends Hrdina{
    public Cisnik(String jmeno) {
        super("Číšník",jmeno, 100,100,30,30,30);
    }

    @Override
    public void utokPrimarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " používá: hod tácem!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSekundarni(Hrdina nepritel) {
        int uder = 0;
        System.out.println(jmeno + " používá: Další runda!");
        nepritel.aktivujOtravu(3);
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSpecialni(Hrdina nepritel) {
        if (mana>=30){
            System.out.println(jmeno + " používá speciální útok: Smršť nádobí!");
            mana = mana - 30;

        } else {
            System.out.println("Nedostatek many!");
        }

    }

}

