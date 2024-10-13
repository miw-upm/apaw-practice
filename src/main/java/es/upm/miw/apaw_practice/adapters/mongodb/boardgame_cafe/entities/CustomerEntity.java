package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class CustomerEntity {
    @Id
    private String email;
    private String name;
    private LocalDate birthDate;
    private boolean isMember;
    private MembershipEntity membership;

    public CustomerEntity() {
        //empty for framework
    }

    public CustomerEntity(Customer customer) {
        this.fromCustomer(customer);
    }

    public void fromCustomer(Customer customer) {
        BeanUtils.copyProperties(customer, this);
        if (customer.getMembership() != null) {
            this.membership = new MembershipEntity(customer.getMembership());
        }
    }

    public Customer toCustomer() {
        Customer customer = new Customer();
        BeanUtils.copyProperties(this, customer);
        if (this.membership != null) {
            customer.setMembership(this.membership.toMembership());
        }
        return customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }

    public MembershipEntity getMembership() {
        return membership;
    }

    public void setMembership(MembershipEntity membership) {
        this.membership = membership;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (email.equals(((CustomerEntity) obj).email));
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isMember=" + isMember +
                ", membership=" + membership +
                '}';
    }
}