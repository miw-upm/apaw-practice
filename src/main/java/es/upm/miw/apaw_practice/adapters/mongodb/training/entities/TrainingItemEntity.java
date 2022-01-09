package es.upm.miw.apaw_practice.adapters.mongodb.training.entities;

import es.upm.miw.apaw_practice.domain.models.training.TrainingItem;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TrainingItemEntity {
    @DBRef
    private LecturerEntity lecturerEntity;
    @Id
    private String id;
    private String name;
    private String knowledgeField;

    public TrainingItemEntity(){
        //empty for framework
    }

    public TrainingItemEntity(LecturerEntity lecturerEntity, String name, String knowledgeField) {
        this.lecturerEntity = lecturerEntity;
        this.name = name;
        this.knowledgeField = knowledgeField;
    }

    public LecturerEntity getLecturerEntity() {
        return lecturerEntity;
    }

    public void setLecturerEntity(LecturerEntity lecturerEntity) {
        this.lecturerEntity = lecturerEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKnowledgeField() {
        return knowledgeField;
    }

    public void setKnowledgeField(String knowledgeField) {
        this.knowledgeField = knowledgeField;
    }

    public TrainingItem toTrainingItem() {
        TrainingItem trainingItem = new TrainingItem();
        BeanUtils.copyProperties(this, trainingItem);
        return trainingItem;
    }

    @Override
    public String toString() {
        return "TrainingItemEntity{" +
                "lecturerEntity=" + lecturerEntity +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", knowledgeField='" + knowledgeField + '\'' +
                '}';
    }
}
