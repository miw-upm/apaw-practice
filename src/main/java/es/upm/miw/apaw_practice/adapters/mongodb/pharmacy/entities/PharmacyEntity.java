package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PharmacyEntity {
    @DBRef
    List<DrugEntity> drugs;
    @Id
    private String registrationNumber;
    private String address;
    private Integer postalCode;

    public PharmacyEntity() {
        //empty from framework
    }

    public PharmacyEntity(Pharmacy pharmacy) {
        BeanUtils.copyProperties(pharmacy, this);
        this.registrationNumber = UUID.randomUUID().toString();
    }

    public PharmacyEntity(String address, Integer postalCode, List<DrugEntity> drugs) {
        this.registrationNumber = UUID.randomUUID().toString();
        this.address = address;
        this.postalCode = postalCode;
        this.drugs = drugs;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public List<DrugEntity> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugEntity> drugs) {
        this.drugs = drugs;
    }

    public Pharmacy toPharmacy() {
        Pharmacy pharmacy = new Pharmacy();
        BeanUtils.copyProperties(this, pharmacy);
        return pharmacy;
    }

    public void fromPharmacy(Pharmacy pharmacy) {
        BeanUtils.copyProperties(pharmacy, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyEntity that = (PharmacyEntity) o;
        return registrationNumber.equals(that.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    @Override
    public String toString() {
        return "PharmacyEntity{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                ", drugs=" + drugs +
                '}';
    }
}
