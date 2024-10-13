package es.upm.miw.apaw_practice.domain.models.e_commerce_model;

import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;

import java.util.List;

public class Customer {

    private String userName;
    private String email;
    private Integer postalCode;
    private ShoppingCart shoppingCart;
    private List<ShippingAddress> shippingAddresses;

    public Customer() {
        // Empty for framework
    }

    public Customer(String userName, String email, Integer postalCode, ShoppingCart shoppingCart) {
        this.userName = userName;
        this.email = email;
        this.postalCode = postalCode;
        this.shoppingCart = shoppingCart;
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", postalCode=" + postalCode +
                ", shoppingCart=" + shoppingCart +
                ", shippingAddresses=" + shippingAddresses +
                '}';
    }
}
