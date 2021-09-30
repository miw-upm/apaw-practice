package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class CoachEntity {
    @Id
    private String id ;
    @Indexed(unique = true)
    private String coachDni;
    private String coachName;
    private String coachFamilyname;
    private Integer coachPhone;
    @DBRef
    private List<LessonEntity> lesson;

    public CoachEntity(){
        //empty
    }
    public CoachEntity(String coachDni, String coachName, String coachFamilyname, List<LessonEntity> lesson) {

        this.id = UUID.randomUUID().toString();
        this.coachDni = coachDni;
        this.coachName = coachName;
        this.coachFamilyname = coachFamilyname;
        this.lesson = lesson;
    }

    public String getId() {return id;}

    public String getCoachDni() {return coachDni;}

    public void setCoachDni(String coachDni) {this.coachDni = coachDni;}

    public String getCoachName() {return coachName;}

    public void setCoachName(String coachName) {this.coachName = coachName;}

    public String getCoachFamilyname() {return coachFamilyname;}

    public void setCoachFamilyname(String coachFamilyname) {this.coachFamilyname = coachFamilyname;}

    public Integer getCoachPhone() {return coachPhone;}

    public void setCoachPhone(Integer coachPhone) {this.coachPhone = coachPhone;}

    public List<LessonEntity> getLesson() {return lesson;}

    public void setLesson(List<LessonEntity> lesson) {this.lesson = lesson;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachEntity that = (CoachEntity) o;return id.equals(that.id) && coachDni.equals(that.coachDni) && Objects.equals(coachName, that.coachName) && Objects.equals(coachFamilyname, that.coachFamilyname) && Objects.equals(coachPhone, that.coachPhone) && Objects.equals(lesson, that.lesson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coachDni, coachName, coachFamilyname, coachPhone, lesson);
    }

    @Override
    public String toString() {
        return "Coachentity{" +
                "id='" + id + '\'' +
                ", coachDni='" + coachDni + '\'' +
                ", coachName='" + coachName + '\'' +
                ", coachFamilyname='" + coachFamilyname + '\'' +
                ", coachPhone=" + coachPhone +
                ", lesson=" + lesson +
                '}';
    }
}
