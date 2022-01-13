package es.upm.miw.apaw_practice.adapters.mongodb.training.entities;

import es.upm.miw.apaw_practice.domain.models.training.Course;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class CourseEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String identity;
    private Integer level;
    private BigDecimal price;

    public CourseEntity() {
        //empty for framework
    }

    public CourseEntity(Course course) {
        BeanUtils.copyProperties(course, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return identity.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (identity.equals(((CourseEntity) obj).identity));
    }

    public Course toCourse() {
        Course course = new Course();
        BeanUtils.copyProperties(this, course);
        return course;
    }

    public void fromCourse(Course course) {
        BeanUtils.copyProperties(course, this);
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id='" + id + '\'' +
                ", identity='" + identity + '\'' +
                ", level=" + level +
                ", price=" + price +
                '}';
    }
}
