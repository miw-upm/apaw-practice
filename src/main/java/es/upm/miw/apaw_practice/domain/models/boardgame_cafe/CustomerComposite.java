package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerComposite implements CustomerTree {
    private final List<CustomerTree> customerTrees;
    private final String groupName;

    public CustomerComposite(String groupName) {
        this.groupName = groupName;
        this.customerTrees = new ArrayList<>();
    }

    @Override
    public String getEmail() {
        return this.customerTrees.stream()
                .map(CustomerTree::getEmail)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getName() {
        return this.groupName + ": " +
                this.customerTrees.stream()
                        .map(CustomerTree::getName)
                        .collect(Collectors.joining(", "));
    }

    @Override
    public LocalDate getBirthDate() {
        throw new UnsupportedOperationException("Cannot get a single birth date from a composite group");
    }

    @Override
    public boolean isMember() {
        return this.customerTrees.stream().allMatch(CustomerTree::isMember);
    }

    @Override
    public Membership getMembership() {
        throw new UnsupportedOperationException("Cannot get a single membership from a composite group");
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(CustomerTree customerTree) {
        this.customerTrees.add(customerTree);
    }

    @Override
    public void remove(CustomerTree customerTree) {
        this.customerTrees.remove(customerTree);
    }

    public List<CustomerTree> getCustomerComponents() {
        return this.customerTrees;
    }

    @Override
    public String toString() {
        return "CustomerComposite{" +
                "groupName='" + groupName + '\'' +
                ", customers=" + customerTrees +
                '}';
    }
}
