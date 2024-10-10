package es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class CustomerEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String userName;

    private String email;
    private Integer postalCode;

    @DBRef
    private ShoppingCartEntity shoppingCart;

    @DBRef
    private List<ShippingAddressEntity> shippingAddresses;

    public CustomerEntity() {
        // Empty constructor for framework
    }

    public CustomerEntity(String customerId, String userName, String email, Integer postalCode, ShoppingCartEntity shoppingCart, List<ShippingAddressEntity> shippingAddresses) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.email = email;
        this.postalCode = postalCode;
        this.shoppingCart = shoppingCart;
        this.shippingAddresses = shippingAddresses;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<ShippingAddressEntity> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(List<ShippingAddressEntity> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", postalCode=" + postalCode +
                ", shoppingCart=" + shoppingCart +
                ", shippingAddresses=" + shippingAddresses +
                '}';
    }
}
