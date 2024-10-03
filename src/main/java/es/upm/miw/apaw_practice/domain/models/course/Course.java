package es.upm.miw.apaw_practice.domain.models.course;

import java.util.UUID;

public class Course {

    private String id;
    private String title;
    private boolean requiresPayment;
    Professor professor;

    public Course() {
        //empty for framework
    }

    public Course(String title, boolean requiresPayment, Professor professor) {
        this.title = title;
        this.requiresPayment = requiresPayment;
        this.professor = professor;
        this.id = generateUniqueId(title != null ?title:"");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequiresPayment() {
        return requiresPayment;
    }

    public void setRequiresPayment(boolean requiresPayment) {
        this.requiresPayment = requiresPayment;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    private String generateUniqueId(String title) {
        long timestamp = System.currentTimeMillis();
        String uniqueUuid = UUID.randomUUID().toString().substring(0, 8);
        return title.replaceAll("\\s+", "") + "-" + timestamp + "-" + uniqueUuid;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", requiresPayment=" + requiresPayment +
                ", professor=" + professor +
                '}';
    }
}
