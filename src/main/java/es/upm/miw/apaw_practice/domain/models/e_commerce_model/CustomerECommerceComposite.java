package es.upm.miw.apaw_practice.domain.models.e_commerce_model;

import java.util.ArrayList;
import java.util.List;

public class CustomerECommerceComposite implements CustomerECommerceTree {

    private final String userName;
    private final String email;
    private final Integer postalCode;
    private final ShoppingCartECommerce shoppingCartECommerce;
    private final List<ShippingAddress> shippingAddresses;
    private final List<CustomerECommerceTree> customerEcommerceTrees;

    public CustomerECommerceComposite(String userName, String email, Integer postalCode,
                                      ShoppingCartECommerce shoppingCartECommerce, List<ShippingAddress> shippingAddresses) {
        this.userName = userName;
        this.email = email;
        this.postalCode = postalCode;
        this.shoppingCartECommerce = shoppingCartECommerce;
        this.shippingAddresses = shippingAddresses;
        this.customerEcommerceTrees = new ArrayList<>();
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Integer getPostalCode() {
        return this.postalCode;
    }

    @Override
    public ShoppingCartECommerce getShoppingCartECommerce() {
        return this.shoppingCartECommerce;
    }

    @Override
    public List<ShippingAddress> getShippingAddresses() {
        return this.shippingAddresses;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(CustomerECommerceTree customerECommerceTree) {
        this.customerEcommerceTrees.add(customerECommerceTree);
    }

    @Override
    public void remove(CustomerECommerceTree customerECommerceTree) {
        this.customerEcommerceTrees.remove(customerECommerceTree);
    }

    @Override
    public List<CustomerECommerceTree> getCustomers() {
        return this.customerEcommerceTrees;
    }
}