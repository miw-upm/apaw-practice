package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDate;

public class Ride {
    private String name;
    private String theme;
    private Integer maxCapacity;
    private LocalDate creationDate;
    private Boolean favourite;

    public Ride() {
        //empty for framework
    }

    public Ride(String name, String theme, Integer maxCapacity, LocalDate creationDate, Boolean favourite) {
        this.name = name;
        this.theme = theme;
        this.maxCapacity = maxCapacity;
        this.creationDate = creationDate;
        this.favourite = favourite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) { this.theme = theme; }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) { this.maxCapacity = maxCapacity; }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "name=" + name +
                ", theme='" + theme + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", creationDate=" + creationDate +
                ", favourite='" + favourite + '\'' +
                '}';
    }
}
