public class Schopnosti {


        private String nazev;
        private String popis;
        private String efekt;
        private int mana;


        public void Schopnost(String nazev, String popis, String efekt, int mana) {
            this.nazev = nazev;
            this.popis = popis;
            this.efekt = efekt;
            this.mana = mana;
        }

        public void vypisPopis() {
            System.out.format(nazev + popis  );
            System.out.println(popis);
            if (mana > 0) {
                System.out.println("Náklady na manu: " + mana);
            }
        }
    }
