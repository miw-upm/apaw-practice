package es.upm.miw.apaw_practice.domain.models.bank;

public class Client {

    private String dni;
    private String name;
    private String surname;
    private int phoneNumber;
    private String email;
    private Address address;

    public Client() {
        // Empty for framework
    }

    public Client(String dni, String name, String surname, int phoneNumber, String email, Address address) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "DNI='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname=" + surname + '\'' +
                ", phone=" + phoneNumber + '\'' +
                ", email=" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
