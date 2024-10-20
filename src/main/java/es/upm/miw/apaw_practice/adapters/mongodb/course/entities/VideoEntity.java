package es.upm.miw.apaw_practice.adapters.mongodb.course.entities;

import es.upm.miw.apaw_practice.domain.models.course.User;
import es.upm.miw.apaw_practice.domain.models.course.Video;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Document
public class VideoEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private LocalTime duration;
    private LocalDateTime creationDate;

    public VideoEntity() {
        //Empty for framework
    }

    public VideoEntity(String name, LocalTime duration, LocalDateTime creationDate) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.duration = duration;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Video toVideo(){
        Video video = new Video();
        BeanUtils.copyProperties(this, video);
        return video;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (name.equals(((VideoEntity) obj).name));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }


    @Override
    public String toString() {
        return "VideoEntity{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", creationDate=" + creationDate +
                '}';
    }
}
