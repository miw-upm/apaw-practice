package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities;

import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class LessonEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private LocalDateTime time;
    private String description;
    private Boolean finished;
    @DBRef
    private List<AthleteEntity> athlete;

    public LessonEntity() {
        //empty for framework
    }

    public LessonEntity(String title, LocalDateTime time, String description, Boolean finished, List<AthleteEntity> athlete) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.time = time;
        this.description = description;
        this.finished = finished;
        this.athlete = athlete;
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

    public List<AthleteEntity> getAthlete() {
        return athlete;
    }

    public void setAthlete(List<AthleteEntity> athlete) {
        this.athlete = athlete;
    }

    public Lesson toLesson() {
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(this, lesson);
        return lesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonEntity that = (LessonEntity) o;
        return title.equals(that.title) && Objects.equals(time, that.time) && Objects.equals(description, that.description) && Objects.equals(finished, that.finished) && Objects.equals(athlete, that.athlete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, description, finished, athlete);
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", finished=" + finished +
                ", athlete=" + athlete +
                '}';
    }
}

