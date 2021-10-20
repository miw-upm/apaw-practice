package es.upm.miw.apaw_practice.adapters.mongodb.Class.entities;

import es.upm.miw.apaw_practice.domain.models.Class.Professor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class ProfessorEntity {

    @Id
    private String id;
    private String name;
    private String course;
    private int age;
    private LocalDate entryDate;

    public ProfessorEntity() {
        //empty for framework
    }

    public ProfessorEntity(String name, String course, int age, LocalDate EntryDate) {
        this.name = name;
        this.course = course;
        this.age = age;
        this.entryDate = EntryDate;
    }

    public ProfessorEntity(Professor professor){
        BeanUtils.copyProperties(professor, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getEntryData() {
        return entryDate;
    }

    public void setEntryDate(LocalDate EntryData) {
        this.entryDate = EntryData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Professor toProfessor() {
        Professor professor = new Professor();
        BeanUtils.copyProperties(this, professor);
        return professor;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ProfessorEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Album{" +
                "name =" + name + '\'' +
                ", course =" + course + '\'' +
                ", age =" + age + '\'' +
                ", EntryDate =" + entryDate + '\'' +
                '}';
    }

}
