package es.upm.miw.apaw_practice.domain.models.E_commerce_model;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;

import java.util.List;

public class Customer {
    private String userName;
    private String email;
    private int postalCode;
    private ShoppingCart shoppingCart;
    private List<ShippingAddress> shippingAddresses;

    public Customer(String userName, String email, int postalCode, ShoppingCart shoppingCart) {
        this.userName = userName;
        this.email = email;
        this.postalCode = postalCode;
        this.shoppingCart = shoppingCart;
    }

    // Getter 和 Setter 方法
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
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

    public void setShippingAddresses (List<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", postalCode=" + postalCode +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
