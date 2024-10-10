package es.upm.miw.apaw_practice.domain.models.E_commerce_model;

public class ShippingAddress {
    private String location;
    private String telefono;
    private String recipientName;

    public ShippingAddress() {
        //Empty for framework
    }

    public ShippingAddress(String location, String telefono, String recipientName) {
        this.location = location;
        this.telefono = telefono;
        this.recipientName = recipientName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "location='" + location + '\'' +
                ", telefono='" + telefono + '\'' +
                ", recipientName='" + recipientName + '\'' +
                '}';
    }
}
