package es.upm.miw.apaw_practice.adapters.rest.cinema.dto;

public class ScreeningDto {
    private String screeningTime; // ISO, ej. "2025-06-08T17:00:00"
    private Boolean threeDFormat;
    private Integer availableSeats;

    public ScreeningDto() {}

    public ScreeningDto(String screeningTime, Boolean threeDFormat, Integer availableSeats) {
        this.screeningTime = screeningTime;
        this.threeDFormat = threeDFormat;
        this.availableSeats = availableSeats;
    }

    public String getScreeningTime() { return screeningTime; }
    public void setScreeningTime(String screeningTime) { this.screeningTime = screeningTime; }

    public Boolean getThreeDFormat() { return threeDFormat; }
    public void setThreeDFormat(Boolean threeDFormat) { this.threeDFormat = threeDFormat; }

    public Integer getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }
}