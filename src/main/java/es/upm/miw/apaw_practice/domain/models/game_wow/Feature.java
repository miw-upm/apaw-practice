package es.upm.miw.apaw_practice.domain.models.game_wow;

public class Feature {

    private String part;
    private int spellPower;
    private int meleeAtack;
    private int temple;
    private String extraEpell;

    public Feature() {
        //empty for framework
    }

    @Override
    public String toString() {
        return "Feature{" +
                "part='" + part + '\'' +
                ", spellPower=" + spellPower +
                ", meleeAtack=" + meleeAtack +
                ", temple=" + temple +
                ", extraEpell='" + extraEpell + '\'' +
                '}';
    }
}
