package es.upm.miw.apaw_practice.domain.models.gym;


import java.time.LocalDateTime;
import java.util.List;

public class Lesson {
    private String title ;
    private LocalDateTime time;
    private String description ;
    private Boolean finished ;
    private List<Athlete> athletes ;


    public Lesson(){
        //empty for framework
    }

    public Lesson(String title, LocalDateTime time, String description, Boolean finished, List<Athlete> athletes) {
        this.title = title;
        this.time = time;
        this.description = description;
        this.finished = finished;
        this.athletes = athletes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", finished=" + finished +
                ", athletes=" + athletes +
                '}';
    }
}
