package es.upm.miw.apaw_practice.domain.models.training;

import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity;

public class TrainingItem {
    private LecturerEntity lecturerEntity;
    private String id;
    private String name;
    private String knowledgeField;

    public TrainingItem() {
        //empty from framework
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

    @Override
    public String toString() {
        return "TrainingItem{" +
                "lecturerEntity=" + lecturerEntity +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", knowledgeField='" + knowledgeField + '\'' +
                '}';
    }
}
