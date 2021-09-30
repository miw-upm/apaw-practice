package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;
import java.util.List;

public class Degree {

    private String title;
    private Integer code;
    private LocalDate implementationDate;
    private List<Subject> subjects;

    public Degree() {
        //empty for framework
    }

    public Degree(String title, Integer code, LocalDate implementationDate, List<Subject> subjects) {
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
