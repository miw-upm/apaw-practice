package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities;

import es.upm.miw.apaw_practice.domain.models.e_commerce_model.CustomerECommerce;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class CustomerEcommerceEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String userName;

    private String email;
    private Integer postalCode;

    @DBRef
    private ShoppingCartECommerceEntity shoppingCart;

    @DBRef
    private List<ShippingAddressEntity> shippingAddresses;

    public CustomerEcommerceEntity() {
        // Empty constructor for framework
    }

    public CustomerEcommerceEntity(String customerId, String userName, String email, Integer postalCode, ShoppingCartECommerceEntity shoppingCart, List<ShippingAddressEntity> shippingAddresses) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.email = email;
        this.postalCode = postalCode;
        this.shoppingCart = shoppingCart;
        this.shippingAddresses = shippingAddresses;
    }

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

    public ShoppingCartECommerceEntity getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartECommerceEntity shoppingCart) {
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
        CustomerEcommerceEntity that = (CustomerEcommerceEntity) o;
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

    public CustomerECommerce toCustomer() {
        CustomerECommerce customer = new CustomerECommerce();
        BeanUtils.copyProperties(this, customer);
        return customer;
    }
}
