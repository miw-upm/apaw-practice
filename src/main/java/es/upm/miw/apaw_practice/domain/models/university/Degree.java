package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;
import java.util.List;

public class Degree {

    private String degreeName;
    private Integer degreeCode;
    private LocalDate implementationDate;
    private List<Subject> subjects;

    public Degree() {
        //empty for framework
    }

    public Degree(String degreeName, Integer degreeCode, LocalDate implementationDate, List<Subject> subjects) {
        this.degreeName = degreeName;
        this.degreeCode = degreeCode;
        this.implementationDate = implementationDate;
        this.subjects = subjects;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Integer getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(Integer degreeCode) {
        this.degreeCode = degreeCode;
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
