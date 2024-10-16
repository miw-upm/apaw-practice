package es.upm.miw.apaw_practice.domain.models.e_commerce_model;

import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;

import java.util.List;

public class CustomerECommerce {

    private String userName;
    private String email;
    private Integer postalCode;
    private ShoppingCartECommerce shoppingCartECommerce;
    private List<ShippingAddress> shippingAddresses;

    public CustomerECommerce() {
        // Empty for framework
    }

    public CustomerECommerce(String userName, String email, Integer postalCode, ShoppingCartECommerce shoppingCartECommerce,List<ShippingAddress> shippingAddresses) {
        this.userName = userName;
        this.email = email;
        this.postalCode = postalCode;
        this.shoppingCartECommerce = shoppingCartECommerce;
        this.shippingAddresses = shippingAddresses;
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

    public ShoppingCartECommerce getShoppingCartECommerce() {
        return shoppingCartECommerce;
    }

    public void setShoppingCartECommerce(ShoppingCartECommerce shoppingCartECommerce) {
        this.shoppingCartECommerce = shoppingCartECommerce;
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
                ", shoppingCart=" + shoppingCartECommerce +
                ", shippingAddresses=" + shippingAddresses +
                '}';
    }

}
