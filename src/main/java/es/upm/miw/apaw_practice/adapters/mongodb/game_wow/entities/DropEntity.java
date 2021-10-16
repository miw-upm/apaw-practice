package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities;

import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class DropEntity {

    @Id
    private String id;
    private String title;
    private String race;
    private Integer level;
    @DBRef
    private FeatureEntity feature;

    public DropEntity() {
        //empty for framework
    }

    public DropEntity(String title, String race, Integer level, FeatureEntity feature) {
        this.title = title;
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

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

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

    public Drop todrop() {
        return new Drop(this.title,this.race,this.level,feature.toFeature());
    }


}
