package es.upm.miw.apaw_practice.domain.models.course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private String title;
    private Boolean requiresPayment;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TutoringSession> tutoringSessions;
    private List<User> users;

    public Course() {
        //empty for framework
    }

    public Course(String title, Boolean requiresPayment, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.requiresPayment = requiresPayment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tutoringSessions = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getRequiresPayment() {
        return requiresPayment;
    }

    public void setRequiresPayment(Boolean requiresPayment) {
        this.requiresPayment = requiresPayment;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTutoringSession(TutoringSession tutoringSession) {
        this.tutoringSessions.add(tutoringSession);
    }

    public TutoringSession getTutoringSession(Integer idTutoringSession) {
        return this.tutoringSessions.get(idTutoringSession);
    }

    public Integer getSizeTutoringSession(){
        return this.tutoringSessions.size();
    }

    public void setUser(User user) {
        this.users.add(user);
    }

    public User getUser(Integer idUser) {
        return this.users.get(idUser);
    }

    public Integer getSizeUser(){
        return this.users.size();
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", requiresPayment=" + requiresPayment +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tutoringSessions=" + tutoringSessions +
                ", users=" + users +
                '}';
    }
}
