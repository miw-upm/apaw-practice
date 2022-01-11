package es.upm.miw.apaw_practice.domain.models.training;

import java.util.List;

public class TrainingItem {
    private String name;
    private String knowledgeField;
    private List<Lecturer> lecturers;

    public TrainingItem() {
        //empty from framework
    }

    public TrainingItem(String name, String knowledgeField, List<Lecturer> lecturers) {
        this.name = name;
        this.knowledgeField = knowledgeField;
        this.lecturers = lecturers;
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

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    @Override
    public String toString() {
        return "TrainingItem{" +
                "name='" + name + '\'' +
                ", knowledgeField='" + knowledgeField + '\'' +
                ", lecturers=" + lecturers +
                '}';
    }
}
