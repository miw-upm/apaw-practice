package es.upm.miw.apaw_practice.domain.models.gym;

import java.util.List;

public class Coach {
    private String coachDni;
    private String coachName;
    private String coachFamilyname;
    private Integer coachPhone;
    private List <Lesson> lesson;



    public Coach(){
       //empty for framework
    }

    public Coach(String coachDni, String coachName, String coachFamilyname, Integer coachPhone, List<Lesson> lesson) {
        this.coachDni = coachDni;
        this.coachName = coachName;
        this.coachFamilyname = coachFamilyname;
        this.coachPhone = coachPhone;
        this.lesson = lesson;
    }

    public String getCoachDni() {
        return coachDni;
    }

    public void setCoachDni(String coachDni) {
        this.coachDni = coachDni;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachFamilyname() {
        return coachFamilyname;
    }

    public void setCoachFamilyname(String coachFamilyname) {
        this.coachFamilyname = coachFamilyname;
    }

    public Integer getCoachPhone() {
        return coachPhone;
    }

    public void setCoachPhone(Integer coachPhone) {
        this.coachPhone = coachPhone;
    }

    public List<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(List<Lesson> lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "coachDni='" + coachDni + '\'' +
                ", coachName='" + coachName + '\'' +
                ", coachFamilyname='" + coachFamilyname + '\'' +
                ", coachPhone=" + coachPhone +
                ", lesson=" + lesson +
                '}';
    }
}
