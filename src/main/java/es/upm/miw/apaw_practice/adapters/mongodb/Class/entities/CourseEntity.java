package es.upm.miw.apaw_practice.adapters.mongodb.Class.entities;

import es.upm.miw.apaw_practice.domain.models.Class.Course;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;

public class CourseEntity {

    @Id
    private String id;
    private String name;
    private int credit;
    private int numberOfStudents;

    public CourseEntity() {
        //empty for framework
    }

    public CourseEntity(String name, int credit, int NumberOfStudents) {
        this.name = name;
        this.credit = credit;
        this.numberOfStudents = NumberOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int NumberOfStudents) {
        this.numberOfStudents = NumberOfStudents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course toCourse(){
        Course course = new Course();
        BeanUtils.copyProperties(this, course);
        return course;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((CourseEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Album{" +
                "name =" + name + '\'' +
                ", credit =" + credit + '\'' +
                ", NumberOfStudents ='" + numberOfStudents + '\'' +
                '}';
    }
}
