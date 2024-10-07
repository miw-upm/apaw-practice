package es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities;

import es.upm.miw.apaw_practice.domain.models.night_life.*;
import es.upm.miw.apaw_practice.domain.models.night_life.Reservation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Document
public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    @DBRef
    private List<ReservationEntity> reservationEntities;

    public CustomerEntity() {
        //empty for framework
    }
    public CustomerEntity(Customer customer) {
        BeanUtils.copyProperties(customer,this);
        this.id = UUID.randomUUID().toString();
    }
    public CustomerEntity(String name, String phone, String email, List<ReservationEntity> reservationEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.reservationEntities = reservationEntities;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<ReservationEntity> getReservationEntities() {
        return reservationEntities;
    }
    public void setReservationEntities(List<ReservationEntity> reservationEntities) {
        this.reservationEntities = reservationEntities;
    }
    public Customer toCustomer() {
        List<Reservation> reservations = this.reservationEntities.stream()
                .map(ReservationEntity::toReservation)
                .collect(Collectors.toList());
        return new Customer(this.name, this.phone, this.email, reservations);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((CustomerEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", reservationEntities=" + reservationEntities.size() +
                '}';
    }

}
