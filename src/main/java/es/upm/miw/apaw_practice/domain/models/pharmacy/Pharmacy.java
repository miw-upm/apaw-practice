package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.util.List;
import java.util.Objects;

public class Pharmacy {

    String registrationNumber;
    String address;
    Integer postalCode;
    List<Drug> drug;

    public Pharmacy() {
        //empty for framework
    }

    public Pharmacy(String address, Integer postalCode, List<Drug> drug) {
        this.address = address;
        this.postalCode = postalCode;
        this.drug = drug;
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

    public List<Drug> getDrug() {
        return drug;
    }

    public void setDrug(List<Drug> drug) {
        this.drug = drug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return registrationNumber.equals(pharmacy.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                ", drug=" + drug +
                '}';
    }
}
