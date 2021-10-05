package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.List;

public class Drop {

    private String drop;
    private Integer level;
    private Feature feature;

    public Drop() {
        //empty for framework
    }

    public String getDrop() {return drop;}

    public void setDrop(String drop) {
        this.drop = drop;
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
                "drop='" + drop + '\'' +
                ", level=" + level +
                ", feature=" + feature +
                '}';
    }
}
