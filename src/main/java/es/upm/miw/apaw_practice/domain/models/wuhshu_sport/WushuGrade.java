package es.upm.miw.apaw_practice.domain.models.wuhshu_sport;

import java.time.LocalDate;

public class WushuGrade {

    LocalDate dateAwarded;
    String gradeTitle;
    Integer gradeLevel;

    public WushuGrade() {
        //empty for framework
    }

    public WushuGrade(LocalDate dateAwarded, String gradeTitle, Integer gradeLevel) {
        this.dateAwarded = dateAwarded;
        this.gradeTitle = gradeTitle;
        this.gradeLevel = gradeLevel;
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

    @Override
    public String toString() {
        return "WushuGrade{" +
                "dateAwarded=" + dateAwarded +
                ", gradeTitle='" + gradeTitle + '\'' +
                ", gradeLevel=" + gradeLevel +
                '}';
    }
}
