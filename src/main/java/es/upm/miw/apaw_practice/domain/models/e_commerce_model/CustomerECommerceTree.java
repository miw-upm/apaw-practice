package es.upm.miw.apaw_practice.domain.models.e_commerce_model;

import java.util.List;

public interface CustomerECommerceTree {
    String getUserName();
    String getEmail();
    Integer getPostalCode();
    ShoppingCartECommerce getShoppingCartECommerce();
    List<ShippingAddress> getShippingAddresses();

    boolean isComposite();
    void add(CustomerECommerceTree customerECommerceTree);
    void remove(CustomerECommerceTree customerECommerceTree);
    List<CustomerECommerceTree> getCustomers();
}
