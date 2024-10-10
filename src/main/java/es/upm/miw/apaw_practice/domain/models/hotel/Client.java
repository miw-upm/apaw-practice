package es.upm.miw.apaw_practice.domain.models.hotel;

public class Client {
    private String identityDocument;
    private String name;
    private String phone;
    private String email;
    private Reservation reservation;

    public Client(){

    }
    public Client(String identityDocument, String name, String phone, String email, Reservation reservation){
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

    public Reservation getReservation() { return this.reservation; }

    public void setReservation(final Reservation reservation) { this.reservation = reservation; }

    @Override
    public String toString() {
        return "Client{" +
                "identityDocument='" + identityDocument + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", reservation=" + reservation +
                '}';
    }
}