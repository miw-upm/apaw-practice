package es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities;
import es.upm.miw.apaw_practice.domain.models.hospital.Disease;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class DiseaseEntity {
    @Id
    private String id;
    private String description;
    private Boolean severe;
    private String alias;

    public DiseaseEntity(){
        //Empty for framework
    }

    public DiseaseEntity(Disease disease){
        BeanUtils.copyProperties(disease, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSevere() {
        return severe;
    }

    public void setSevere(Boolean severe) {
        this.severe = severe;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Disease toDisease(){
        Disease disease = new Disease();
        BeanUtils.copyProperties(this, disease);
        return disease;
    }

    @Override
    public String toString() {
        return "DiseaseEntity{" +
                "description='" + description + '\'' +
                ", severe=" + severe +
                ", alias='" + alias + '\'' +
                '}';
    }
}
