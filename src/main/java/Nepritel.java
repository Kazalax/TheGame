
    public abstract class Nepritel extends Hrdina {
        public Nepritel(String jmeno, int zivoty, int maxZivoty, int sila, int mana, int maxMana) {
            super(jmeno, zivoty, maxZivoty, sila, mana, maxMana);
            this.jmeno = jmeno;
        }

        @Override
        public abstract void utokPrimarni(Hrdina nepritel);
        @Override
        public abstract void utokSekundarni(Hrdina nepritel);
        @Override
        public abstract void utokSpecialni(Hrdina nepritel);

    }
    class UcitelMatematiky extends Nepritel{
        public UcitelMatematiky(){super("Učitel Matematiky",80,80,15,30,30);}
        @Override
        public void utokPrimarni(Hrdina nepritel){
            int uder = sila + random.nextInt(5);
            System.out.println(jmeno + " útočí Hodem křídou a způsobuje" + uder + " poškození!");
            nepritel.prijmoutUtok(uder);
        }

        @Override
        public void utokSekundarni(Hrdina nepritel) {
            int uder = sila + random.nextInt(10);
            System.out.println(jmeno + " útočí Neohlášenou písemkou a způsobuje " + uder + " poškození!");
            nepritel.prijmoutUtok(uder);
        }


        @Override
        public void utokSpecialni(Hrdina nepritel) {
            if (mana>=30){
                System.out.println(jmeno + " používá speciální útok: Jídlo ze školní jídelny a způsobije otravu na 2 tahy!");
                mana = mana - 30;
                nepritel.aktivujOtravu(2);
            }

        }
        public String getJmeno() {
            return jmeno;
        }
    }
    class StinnyStrom extends Nepritel{
        public StinnyStrom(){super("Stinný Strom",100,100,5,30,30);}
        @Override
        public void utokPrimarni(Hrdina nepritel){
            int uder = sila + random.nextInt(10);
            System.out.println(jmeno + " útočí Tanecem stínů a způsobuje " + uder + " poškození!");
            nepritel.prijmoutUtok(uder);
        }

        @Override
        public void utokSekundarni(Hrdina nepritel) {
            int uder = sila + random.nextInt(15);
            System.out.println(jmeno + " útočí Listovou smrští a způsobuje " + uder + " poškození!");
            nepritel.prijmoutUtok(uder);
        }


        @Override
        public void utokSpecialni(Hrdina nepritel) {
            if (mana>=30){
                int uder = sila + random.nextInt(15);
                System.out.println(jmeno + " používá speciální útok: Plodový bombardér a způsobuje " + uder + " poškození!");
                mana = mana - 30;
                nepritel.aktivujOmraceni(2);
            }

        }
        public String getJmeno() {
            return jmeno;
        }
    }
    class Bezdomovec extends Nepritel{
        public Bezdomovec() {
            super("Král odpadků",60,60,10,0,0);
        }
        @Override
        public void utokPrimarni(Hrdina nepritel){
            int uder = sila + random.nextInt(10);
            System.out.println(jmeno + " útočí flaškou od piva a způsobuje " + uder + " poškození!");
            nepritel.prijmoutUtok(uder);
        }

        @Override
        public void utokSekundarni(Hrdina nepritel) {
            int uder = sila + random.nextInt(10);
            System.out.println(jmeno + " útočí hodem krabicákem a způsobuje " + uder + " poškození!");
            nepritel.prijmoutUtok(uder);
        }


        @Override
        public void utokSpecialni(Hrdina nepritel) {
            System.out.println(jmeno + "se ožral a nemůže vstát!");
        }
        public String getJmeno() {
            return jmeno;
        }
    }