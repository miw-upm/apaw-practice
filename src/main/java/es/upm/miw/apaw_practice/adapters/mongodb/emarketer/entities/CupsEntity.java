package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class CupsEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String cups;
    private BigDecimal energy;
    private LocalDateTime registrationDate;
    @DBRef
    private CustomerEntity customerEntity;

    public CupsEntity() {
        //empty for framework
    }

    public CupsEntity( String cups, BigDecimal energy, LocalDateTime registrationDate, CustomerEntity customerEntity) {
        this.id = UUID.randomUUID().toString();
        this.cups = cups;
        this.energy = energy;
        this.registrationDate = LocalDateTime.now();
        this.customerEntity = customerEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCups() {
        return cups;
    }

    public void setCups(String cups) {
        this.cups = cups;
    }

    public BigDecimal getEnergy() {
        return energy;
    }

    public void setEnergy(BigDecimal energy) {
        this.energy = energy;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((CupsEntity) obj).id));
    }

    @Override
    public String toString() {
        return "CupsEntity{" +
                "cups='" + cups + '\'' +
                ", energy=" + energy +
                ", registrationDate=" + registrationDate +
                ", customerEntity=" + customerEntity +
                '}';
    }

}
