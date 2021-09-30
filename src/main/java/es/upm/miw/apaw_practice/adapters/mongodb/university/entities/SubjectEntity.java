package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

public class SubjectEntity {

    private String topic;
    private Integer reference;
    private Integer credits;
    private ClassroomEntity classroom;

    public SubjectEntity() {
        //empty for framework
    }

    public SubjectEntity(String topic, Integer reference, Integer credits, ClassroomEntity classroom) {
        this.topic = topic;
        this.reference = reference;
        this.credits = credits;
        this.classroom = classroom;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public ClassroomEntity getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomEntity classroom) {
        this.classroom = classroom;
    }
}
