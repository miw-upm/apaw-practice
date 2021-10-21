package es.upm.miw.apaw_practice.domain.models.game_wow;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Boss {

    private String description;
    private String effort;
    private List<Drop> dropList;

    public Boss() {
        //empty for framework
    }

    public Boss(String description, String effort, List<Drop> dropList) {
        this.description = description;
        this.effort = effort;
        this.dropList = dropList;
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

    public BossEntity toBossEntity(Boss boss){
        return new BossEntity(
                this.description,this.effort,boss.getDropList().stream().map(drop -> drop.toDropEntity(drop)).collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(this == obj)
            return true;

        Boss objboss = (Boss) obj;

        if(this.description.equalsIgnoreCase(objboss.description) &&
                this.effort.equalsIgnoreCase(objboss.effort) &&
                this.dropList.equals(objboss.dropList))
            return true;

        return false;
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
