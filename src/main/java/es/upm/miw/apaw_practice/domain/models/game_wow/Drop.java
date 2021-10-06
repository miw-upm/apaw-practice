package es.upm.miw.apaw_practice.domain.models.game_wow;

public class Drop {

    private String race;
    private Integer level;
    private Feature feature;

    public Drop() {
        //empty for framework
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Drop{" +
                "drop='" + race + '\'' +
                ", level=" + level +
                ", feature=" + feature +
                '}';
    }
}
