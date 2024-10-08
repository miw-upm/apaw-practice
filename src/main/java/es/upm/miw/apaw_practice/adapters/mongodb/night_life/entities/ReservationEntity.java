package es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class ReservationEntity {
    @Id
    private String id;
    private LocalDate date;
    private BigDecimal price;
    private Integer numberOfPeople;
    @DBRef
    private ClubEntity clubEntity;
    @DBRef
    private List<CustomerEntity> customerEntities;

    public ReservationEntity() {
        //empty for framework
    }

    public ReservationEntity(LocalDate date, BigDecimal price, Integer numberOfPeople, ClubEntity clubEntity, List<CustomerEntity> customerEntities) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.price = price;
        this.numberOfPeople = numberOfPeople;
        this.clubEntity = clubEntity;
        this.customerEntities = customerEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public ClubEntity getClubEntity() {
        return clubEntity;
    }

    public void setClubEntity(ClubEntity clubEntity) {
        this.clubEntity = clubEntity;
    }

    public List<CustomerEntity> getCustomerEntities() {
        return customerEntities;
    }
    public void setCustomerEntities(List<CustomerEntity> customerEntities) {
        this.customerEntities = customerEntities;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ReservationEntity) obj).id));
    }

    @Override
    public String toString() {
        return "ReservationEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", numberOfPeople=" + numberOfPeople +
                ", clubEntity=" + (clubEntity != null ? clubEntity.toString() : "null") +
                ", customerEntities=" + customerEntities +
                '}';
    }

}
