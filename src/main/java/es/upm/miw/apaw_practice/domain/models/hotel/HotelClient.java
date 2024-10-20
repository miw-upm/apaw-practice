package es.upm.miw.apaw_practice.domain.models.hotel;

public class HotelClient {
    private String identityDocument;
    private String name;
    private String phone;
    private String email;
    private HotelReservation hotelReservation;

    public HotelClient(){

    }
    public HotelClient(String identityDocument, String name, String phone, String email, HotelReservation hotelReservation){
        this.identityDocument = identityDocument;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.hotelReservation = hotelReservation;
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

    public HotelReservation getReservation() { return this.hotelReservation; }

    public void setReservation(HotelReservation hotelReservation) { this.hotelReservation = hotelReservation; }

    @Override
    public String toString() {
        return "HotelClientRepository{" +
                "identityDocument='" + identityDocument + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", HotelReservation=" + hotelReservation +
                '}';
    }
}