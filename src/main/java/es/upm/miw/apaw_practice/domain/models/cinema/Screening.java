package es.upm.miw.apaw_practice.domain.models.cinema;

public class Screening {
    private String id;
    private String movieId;
    private String dateTime;
    private Boolean threeDFormat;
    private Integer roomNumber;

    public Screening() {}

    public Screening(String id, String movieId, String dateTime, Boolean threeDFormat, Integer roomNumber) {
        this.id = id;
        this.movieId = movieId;
        this.dateTime = dateTime;
        this.threeDFormat = threeDFormat;
        this.roomNumber = roomNumber;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public Boolean getThreeDFormat() { return threeDFormat; }
    public void setThreeDFormat(Boolean threeDFormat) { this.threeDFormat = threeDFormat; }
    public Integer getRoomNumber() { return roomNumber; } // <--- Añadido
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; } // <--- Añadido
}