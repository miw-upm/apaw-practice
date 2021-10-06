package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.List;

public class Boss {

    private String description;
    private String effort;
    private List<Drop> dropList;

    public Boss() {
        //empty for framework
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public List<Drop> getDropList() {
        return dropList;
    }

    public void setDropList(List<Drop> dropList) {
        this.dropList = dropList;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "boss='" + description + '\'' +
                ", effort='" + effort + '\'' +
                ", bossesList=" + dropList +
                '}';
    }
}
