public class Main {
    public static void main(String[] args) {

        Intro intro = new Intro();

        Hrdina vybranyHrdina = intro.vyberHrdinu();
        if (vybranyHrdina != null) {
            Mapa mapa = new Mapa(vybranyHrdina);
            mapa.umistiHrace();
            mapa.PohniHracem();
        }
    }
}
