package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDate;

public class CustomerLeaf implements CustomerTree {
    private final Customer customer;

    public CustomerLeaf(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String getEmail() {
        return customer.getEmail();
    }

    @Override
    public String getName() {
        return customer.getName();
    }

    @Override
    public LocalDate getBirthDate() {
        return customer.getBirthDate();
    }

    @Override
    public boolean isMember() {
        return customer.isMember();
    }

    @Override
    public Membership getMembership() {
        return customer.getMembership();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(CustomerTree customerTree) {
        throw new UnsupportedOperationException("Cannot add to a leaf");
    }

    @Override
    public void remove(CustomerTree customerTree) {
        throw new UnsupportedOperationException("Cannot remove from a leaf");
    }

    @Override
    public String toString() {
        return "CustomerLeaf{" +
                "email='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", isMember=" + isMember() +
                ", membership=" + getMembership() +
                '}';
    }
}
