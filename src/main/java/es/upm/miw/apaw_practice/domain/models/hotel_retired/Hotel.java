package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.util.Objects;

public class Hotel {

    private String cif;
    private String hotelName;
    private String address;

    public Hotel() {
        // empty for framework
    }

    public Hotel(String cif, String hotelName, String address) {
        this.cif = cif;
        this.hotelName = hotelName;
        this.address = address;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(cif, hotel.cif) && Objects.equals(hotelName, hotel.hotelName) && Objects.equals(address, hotel.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cif, hotelName, address);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "cif='" + cif + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
