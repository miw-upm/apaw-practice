package es.upm.miw.apaw_practice.domain.models.e_commerce_model;

import java.util.List;

public class CustomerECommerceLeaf implements CustomerECommerceTree {
    private final CustomerECommerce customerECommerce;

    public CustomerECommerceLeaf(CustomerECommerce customerECommerce) {
        this.customerECommerce = customerECommerce;
    }

    @Override
    public String getUserName() {
        return this.customerECommerce.getUserName();
    }

    @Override
    public String getEmail() {
        return this.customerECommerce.getEmail();
    }

    @Override
    public Integer getPostalCode() {
        return this.customerECommerce.getPostalCode();
    }

    @Override
    public ShoppingCartECommerce getShoppingCartECommerce() {
        return this.customerECommerce.getShoppingCartECommerce();
    }

    @Override
    public List<ShippingAddress> getShippingAddresses() {
        return this.customerECommerce.getShippingAddresses();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(CustomerECommerceTree customerECommerceTree) {
        throw new UnsupportedOperationException("Unsupported operation in leaf");
    }

    @Override
    public void remove(CustomerECommerceTree customerECommerceTree) {
        // Do nothing because it is a leaf
    }

    @Override
    public List<CustomerECommerceTree> getCustomers() {
        return List.of();
    }

    @Override
    public String toString() {
        return "CustomerECommerceLeaf{" +
                "customerECommerce=" + customerECommerce +
                '}';
    }
}
