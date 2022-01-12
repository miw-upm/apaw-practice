package es.upm.miw.apaw_practice.domain.models.training;

import java.math.BigDecimal;

public class Course {
    private String identity;
    private Integer level;
    private BigDecimal price;

    public Course() {
        //empty from framework
    }

    public Course(String identity, Integer level, BigDecimal price) {
        this.identity = identity;
        this.level = level;
        this.price = price;
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

    public static Course ofIdentity(Course course) {
        Course courseDto = new Course();
        courseDto.setIdentity(course.getIdentity());
        return courseDto;
    }

    @Override
    public String toString() {
        return "Course{" +
                "identity='" + identity + '\'' +
                ", level=" + level +
                ", price=" + price +
                '}';
    }
}
