import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mistnost {
    private String popis;
    private List<String> predmety;
    private Map<String, Mistnost> vychody;

    public Mistnost(String popis) {
        this.popis = popis;
        this.predmety = new ArrayList<>();
        this.vychody = new HashMap<>();
    }

    public String getPopis() {
        return popis;
    }
    public List<String> getPredmety(){
        return predmety;
    }
    public void addPredmet(String predmet){
        predmety.add(predmet);
    }
    public Mistnost getvychod(String smer){
        return vychody.get(smer);
    }
}
