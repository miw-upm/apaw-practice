package es.upm.miw.apaw_practice.domain.models.gym;


import java.time.LocalDateTime;
import java.util.List;

public class Lesson {
    private String title ;
    private LocalDateTime time;
    private String description;
    private Boolean finished;
    private List<Athlete> athletes;


    public Lesson() {
        //empty for framework
    }

    public static LessonBuilders.Title builder() {
        return new Builder();
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

    public static class Builder implements LessonBuilders.Title, LessonBuilders.Description, LessonBuilders.Time,
            LessonBuilders.Finished, LessonBuilders.Athlete, LessonBuilders.Optionals {

        private Lesson lesson;

        public Builder() {
            this.lesson = new Lesson();
        }

        @Override
        public LessonBuilders.Time title(String title) {
            this.lesson.title = title;
            return this;
        }

        @Override
        public LessonBuilders.Description time(LocalDateTime time) {
            this.lesson.time = time;
            return this;
        }

        @Override
        public LessonBuilders.Finished description(String description) {
            this.lesson.description = description;
            return this;
        }

        @Override
        public LessonBuilders.Athlete finished(Boolean finished) {
            this.lesson.finished = finished;
            return this;
        }

        @Override
        public LessonBuilders.Optionals athletes(List<Athlete> athletes) {
            this.lesson.athletes = athletes;
            return this;
        }

        @Override
        public Lesson build() {
            return this.lesson;
        }


    }
}
