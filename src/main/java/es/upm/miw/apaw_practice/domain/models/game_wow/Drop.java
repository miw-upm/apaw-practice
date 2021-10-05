package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.List;

public class Drop {

    private String drop;
    private int level;
    private List<Boss> bossesList;
    private Feature feature;

    public Drop() {
        //empty for framework
    }

    @Override
    public String toString() {
        return "Drop{" +
                "drop='" + drop + '\'' +
                ", level=" + level +
                ", bossesList=" + bossesList +
                ", feature=" + feature +
                '}';
    }
}
