package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class DropEntity {

    @Id
    private String id;
    private String race;
    private Integer level;
    @DBRef
    private FeatureEntity feature;

    public DropEntity() {
        //empty for framework
    }

    public DropEntity(String race, Integer level, FeatureEntity feature) {
        this.race = race;
        this.level = level;
        this.feature = feature;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public FeatureEntity getFeature() {
        return feature;
    }

    public void setFeature(FeatureEntity feature) {
        this.feature = feature;
    }
}
