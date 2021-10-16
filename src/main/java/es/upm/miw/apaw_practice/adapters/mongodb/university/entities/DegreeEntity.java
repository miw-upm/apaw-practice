package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import es.upm.miw.apaw_practice.domain.models.university.Degree;
import es.upm.miw.apaw_practice.domain.models.university.Subject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DegreeEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private Integer code;
    private String title;
    private LocalDate implementationDate;
    private List<SubjectEntity> subjects;

    public DegreeEntity() {
        //empty for framework
    }

    public DegreeEntity(Integer code, String title, LocalDate implementationDate) {
        this(code, title, implementationDate, null);
    }

    public DegreeEntity(Integer code, String title, LocalDate implementationDate, List<SubjectEntity> subjects) {
        this.code = code;
        this.title = title;
        this.implementationDate = implementationDate;
        this.subjects = subjects;
        this.id = UUID.randomUUID().toString();
    }

    public Degree toDegree() {
        if(this.subjects != null) {
            List<Subject> subjects = this.subjects.stream()
                    .map(SubjectEntity::toSubject)
                    .collect(Collectors.toList());
            return new Degree(code, title, implementationDate, subjects);
        }
        return new Degree(code, title, implementationDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
