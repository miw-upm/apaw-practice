package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities;

import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import java.util.UUID;

@Document
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String idMembership;
    private String address;
    private LocalDateTime entranceDate;
    private Boolean oneYearMembership;

    public UserEntity() {
        //empty for framework
    }

    public UserEntity(User user) {
        BeanUtils.copyProperties(user, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMembership() {
        return idMembership;
    }

    public void setIdMembership(String idMembership) {
        this.idMembership = idMembership;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(LocalDateTime entranceDate) {
        this.entranceDate = entranceDate;
    }

    public Boolean getOneYearMembership() {
        return oneYearMembership;
    }

    public void setOneYearMembership(Boolean oneYearMembership) {
        this.oneYearMembership = oneYearMembership;
    }

    public User toUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    public void fromUser(User user) {
        BeanUtils.copyProperties(user, this);
    }

    @Override
    public int hashCode() {
        return idMembership.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (idMembership.equals(((UserEntity) obj).idMembership));
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", idMembership='" + idMembership + '\'' +
                ", address='" + address + '\'' +
                ", entranceDate=" + entranceDate +
                ", oneYearMembership=" + oneYearMembership +
                '}';
    }
}
