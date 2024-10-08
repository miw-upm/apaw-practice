package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities;

import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import es.upm.miw.apaw_practice.domain.models.theme_park.Ride;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class RideEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String theme;
    private LocalDate openingDate;
    private Integer maxCapacity;
    @DBRef
    private List<UserEntity> userEntities;
    private Boolean favourite;

    public RideEntity() {
        //empty for framework
    }

    public RideEntity(String name, String theme, LocalDate openingDate,Integer maxCapacity, List<UserEntity> userEntities, Boolean favourite) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.theme = theme;
        this.openingDate = openingDate;
        this.maxCapacity = maxCapacity;
        this.userEntities = userEntities;
        this.favourite = favourite;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

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

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Ride toRide() {
        List<User> users = this.userEntities.stream()
                .map(UserEntity::toUser)
                .toList();
        return new Ride(name, theme, maxCapacity, openingDate, favourite, users);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (name.equals(((RideEntity) obj).name));
    }

    @Override
    public String toString() {
        return "RideEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", theme=" + theme +
                ", openingDate='" + openingDate + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", userEntities=" + userEntities +
                ", favourite=" + favourite +
                '}';
    }
}
