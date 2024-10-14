package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class HotelClientEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String identityDocument;
    private String name;
    private String phone;
    private String email;
    private HotelReservationEntity reservation;

    public HotelClientEntity(){

    }
    public HotelClientEntity(String identityDocument, String name, String phone, String email, HotelReservationEntity reservation){
        this.identityDocument = identityDocument;
        this.name = name;
        this.phone = phone;
        this.reservation = reservation;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
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

    public HotelReservationEntity getReservation() { return this.reservation; }

    public void setReservation(final HotelReservationEntity reservation) { this.reservation = reservation; }

    @Override
    public String toString() {
        return "HotelClientRepository{" +
                "identityDocument='" + identityDocument + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", reservation=" + reservation +
                '}';
    }
}