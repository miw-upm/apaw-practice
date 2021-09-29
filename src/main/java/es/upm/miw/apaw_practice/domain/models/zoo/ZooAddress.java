package es.upm.miw.apaw_practice.domain.models.zoo;

public class ZooAddress {

    private String street;
    private Integer streetNumber;
    private String zipCode;

    public ZooAddress(String street, Integer streetNumber, String zipCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street=" + street +
                ", streetNumber='" + streetNumber + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
