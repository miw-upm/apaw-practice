package es.upm.miw.apaw_practice.domain.models.game_wow;

public class Feature {

    private String part;
    private Integer spellPower;
    private Integer meleeAtack;
    private Integer temple;
    private String extraSpell;

    public Feature() {
        //empty for framework
    }

    public Feature(String part, Integer spellPower, Integer meleeAtack, Integer temple, String extraSpell) {
        this.part = part;
        this.spellPower = spellPower;
        this.meleeAtack = meleeAtack;
        this.temple = temple;
        this.extraSpell = extraSpell;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Integer getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(Integer spellPower) {
        this.spellPower = spellPower;
    }

    public Integer getMeleeAtack() {
        return meleeAtack;
    }

    public void setMeleeAtack(Integer meleeAtack) {
        this.meleeAtack = meleeAtack;
    }

    public Integer getTemple() {
        return temple;
    }

    public void setTemple(Integer temple) {
        this.temple = temple;
    }

    public String getExtraSpell() {
        return extraSpell;
    }

    public void setExtraSpell(String extraSpell) {
        this.extraSpell = extraSpell;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "part='" + part + '\'' +
                ", spellPower=" + spellPower +
                ", meleeAtack=" + meleeAtack +
                ", temple=" + temple +
                ", extraEpell='" + extraSpell + '\'' +
                '}';
    }
}
