package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.List;

public class Boss {

    private String boss;
    private String effort;
    private List<Drop> DropList;

    public Boss() {
        //empty for framework
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public List<Drop> getDropList() {
        return DropList;
    }

    public void setDropList(List<Drop> dropList) {
        DropList = dropList;
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
