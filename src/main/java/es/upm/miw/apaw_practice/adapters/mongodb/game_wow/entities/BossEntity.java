package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class BossEntity {

    @Id
    private String id;
    private String description;
    private String effort;
    @DBRef
    private List<DropEntity> dropList;

    public BossEntity() {
        //empty for framework
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<DropEntity> getDropList() {
        return dropList;
    }

    public void setDropList(List<DropEntity> dropList) {
        this.dropList = dropList;
    }
}
