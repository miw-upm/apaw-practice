package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.List;

public class Boss {

    private String boss;
    private String effort;
    private List<Drop> DropList;

    public Boss() {
        //empty for framework
    }

    @Override
    public String toString() {
        return "Boss{" +
                "boss='" + boss + '\'' +
                ", effort='" + effort + '\'' +
                ", bossesList=" + DropList +
                '}';
    }
}
