package es.upm.miw.apaw_practice.domain.models.course;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private String title;
    private Boolean paymentRequired;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TutoringSession> tutoringSessions;
    private List<User> users;

    public Course() {
        //empty for framework
    }

    public Course(String title, Boolean paymentRequired, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.paymentRequired = paymentRequired;
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

    public Boolean getPaymentRequired() {
        return paymentRequired;
    }

    public void setPaymentRequired(Boolean paymentRequired) {
        this.paymentRequired = paymentRequired;
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

    public List<TutoringSession> getTutoringSessions() {
        return tutoringSessions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setTutoringSession(TutoringSession tutoringSession) {
        this.tutoringSessions.add(tutoringSession);
    }

    public void setTutoringSessions(List<TutoringSession> tutoringSessions) {
        this.tutoringSessions = tutoringSessions;
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

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser(Integer idUser) {
        return this.users.get(idUser);
    }

    public Integer getSizeUser(){
        return this.users.size();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (title.equals(((Course) obj).getTitle()));
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", paymentRequired=" + paymentRequired +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tutoringSessions=" + tutoringSessions +
                ", users=" + users +
                '}';
    }
}
