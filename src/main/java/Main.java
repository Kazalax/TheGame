import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       Intro intro = new Intro(); // Create an instance of the Intro class

        Hrdina vybranyHrdina = intro.vyberHrdinu();
        if (vybranyHrdina != null) {
            Mapa mapa = new Mapa(vybranyHrdina);
        mapa.umistiHrace();
        mapa.PohniHracem();
        }
    }
}
