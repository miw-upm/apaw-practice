package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import java.time.LocalDate;
import java.util.List;

public class DegreeEntity {

    private String title;
    private Integer code;
    private LocalDate implementationDate;
    private List<SubjectEntity> subjects;

    public DegreeEntity() {
        //empty for framework
    }

    public DegreeEntity(String title, Integer code, LocalDate implementationDate, List<SubjectEntity> subjects) {
        this.title = title;
        this.code = code;
        this.implementationDate = implementationDate;
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LocalDate getImplementationDate() {
        return implementationDate;
    }

    public void setImplementationDate(LocalDate implementationDate) {
        this.implementationDate = implementationDate;
    }

    public List<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectEntity> subjects) {
        this.subjects = subjects;
    }
}
