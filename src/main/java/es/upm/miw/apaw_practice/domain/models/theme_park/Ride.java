package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDate;
import java.util.List;

public class Ride {
    private String name;
    private String theme;
    private Integer maxCapacity;
    private LocalDate openingDate;
    private Boolean favourite;
    private List<User> users;

    public Ride() {
        //empty for framework
    }

    public Ride(String name, String theme, Integer maxCapacity, LocalDate openingDate, Boolean favourite, List<User> users) {
        this.name = name;
        this.theme = theme;
        this.maxCapacity = maxCapacity;
        this.openingDate = openingDate;
        this.favourite = favourite;
        this.users = users;
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

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "name=" + name +
                ", theme='" + theme + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", openingDate=" + openingDate +
                ", favourite='" + favourite + '\'' +
                ", usersAddress='" + users.stream().map(User::getAddress).toList() +
                '}';
    }
}
