public class Nepritel extends Hrdina {
    public Nepritel(String jmeno) {
        super("Bezdomovec",jmeno, 100, 100, 10, 20, 20);
    }

    @Override
    public void utokPrimarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " útočí primárním útokem!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSekundarni(Hrdina nepritel) {
        int uder = sila + random.nextInt(10);
        System.out.println(jmeno + " útočí sekundárním útokem!");
        nepritel.prijmoutUtok(uder);
    }

    @Override
    public void utokSpecialni(Hrdina nepritel) {
        System.out.println(jmeno + " používá speciální útok, ale nic se nestalo!");
    }
}
