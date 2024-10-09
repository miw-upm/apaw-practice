package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class WushuGradeEntity {

    @Id
    private String id;
    private LocalDate dateAwarded;
    private String gradeTitle;
    private Integer gradeLevel;

    public WushuGradeEntity() {
        //empty from framework
    }

    public WushuGradeEntity(WushuGrade wushuGrade) {
        BeanUtils.copyProperties(wushuGrade, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(LocalDate dateAwarded) {
        this.dateAwarded = dateAwarded;
    }

    public String getGradeTitle() {
        return gradeTitle;
    }

    public void setGradeTitle(String gradeTitle) {
        this.gradeTitle = gradeTitle;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public WushuGrade toWushuGrade() {
        WushuGrade wushuGrade = new WushuGrade();
        BeanUtils.copyProperties(this, wushuGrade);
        return wushuGrade;
    }

    @Override
    public String toString() {
        return "WuhsuGradeEntity{" +
                "id='" + id + '\'' +
                ", dateAwarded=" + dateAwarded +
                ", gradeTitle='" + gradeTitle + '\'' +
                ", gradeLevel=" + gradeLevel +
                '}';
    }
}
