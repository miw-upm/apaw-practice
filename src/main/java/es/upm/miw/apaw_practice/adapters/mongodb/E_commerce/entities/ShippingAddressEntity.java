package es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class ShippingAddressEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String location;
    private String telefono;
    private String recipientName;

    public ShippingAddressEntity() {
        //Empty for framework
    }

    public ShippingAddressEntity(String location, String telefono, String recipientName) {
        this.id = UUID.randomUUID().toString();
        this.location = location;
        this.telefono = telefono;
        this.recipientName = recipientName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingAddressEntity that = (ShippingAddressEntity) o;
        return Objects.equals(location, that.location);
    }
    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
    @Override
    public String toString() {
        return "ShippingAddressEntity{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", telefono='" + telefono + '\'' +
                ", recipientName='" + recipientName + '\'' +
                '}';
    }
}
