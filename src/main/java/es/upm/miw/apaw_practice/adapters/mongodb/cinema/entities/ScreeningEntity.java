package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "screenings")
public class ScreeningEntity {
    @Id
    private String id;
    private LocalDateTime screeningTime;
    private Boolean threeDFormat;
    private Integer roomNumber;
    private String movieId;

    public ScreeningEntity() {}

    public ScreeningEntity(String id, LocalDateTime screeningTime, Boolean threeDFormat, Integer roomNumber, String movieId) {
        this.id = id;
        this.screeningTime = screeningTime;
        this.threeDFormat = threeDFormat;
        this.roomNumber = roomNumber;
        this.movieId = movieId;
    }

    public ScreeningEntity(LocalDateTime screeningTime, Boolean threeDFormat, Integer roomNumber, String movieId) {
        this(null, screeningTime, threeDFormat, roomNumber, movieId);
    }

    public static ScreeningEntity fromScreening(Screening screening) {
        ScreeningEntity entity = new ScreeningEntity();
        entity.id = screening.getId();
        entity.screeningTime = screening.getDateTime() != null ? LocalDateTime.parse(screening.getDateTime()) : null;
        entity.threeDFormat = screening.getThreeDFormat();
        entity.roomNumber = screening.getRoomNumber();
        entity.movieId = screening.getMovieId();
        return entity;
    }

    public Screening toScreening() {
        Screening screening = new Screening();
        screening.setId(this.id);
        screening.setDateTime(this.screeningTime != null ? this.screeningTime.toString() : null);
        screening.setThreeDFormat(this.threeDFormat);
        screening.setRoomNumber(this.roomNumber);
        screening.setMovieId(this.movieId);
        return screening;
    }

    public void updateFromScreening(Screening screening) {
        // this.id = screening.getId(); // Solo si quieres permitir actualizar el id
        this.screeningTime = screening.getDateTime() != null ? LocalDateTime.parse(screening.getDateTime()) : null;
        this.threeDFormat = screening.getThreeDFormat();
        this.roomNumber = screening.getRoomNumber();
        this.movieId = screening.getMovieId();
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDateTime getScreeningTime() { return screeningTime; }
    public void setScreeningTime(LocalDateTime screeningTime) { this.screeningTime = screeningTime; }
    public Boolean getThreeDFormat() { return threeDFormat; }
    public void setThreeDFormat(Boolean threeDFormat) { this.threeDFormat = threeDFormat; }
    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }
    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }
}