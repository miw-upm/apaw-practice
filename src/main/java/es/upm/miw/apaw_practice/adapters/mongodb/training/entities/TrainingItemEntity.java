package es.upm.miw.apaw_practice.adapters.mongodb.training.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class TrainingItemEntity {
    @DBRef
    private CourseEntity courseEntity;
    @DBRef
    private List<LecturerEntity> lecturerList;
    private String name;
    private String knowledgeField;

    public TrainingItemEntity(){
        //empty for framework
    }

    public TrainingItemEntity(CourseEntity courseEntity, List<LecturerEntity> lecturerList, String name, String knowledgeField) {
        this.courseEntity = courseEntity;
        this.lecturerList = lecturerList;
        this.name = name;
        this.knowledgeField = knowledgeField;
    }

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    public List<LecturerEntity> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(List<LecturerEntity> lecturerList) {
        this.lecturerList = lecturerList;
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
        return "TrainingItemEntity{" +
                "courseEntity=" + courseEntity +
                ", lecturerList=" + lecturerList +
                ", name='" + name + '\'' +
                ", knowledgeField='" + knowledgeField + '\'' +
                '}';
    }
}
