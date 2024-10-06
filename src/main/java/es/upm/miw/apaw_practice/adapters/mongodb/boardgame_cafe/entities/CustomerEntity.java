package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
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

    public CustomerEntity() {
        //empty for framework
    }

    public CustomerEntity(Customer customer) {
        this.fromCustomer(customer);
    }

    public void fromCustomer(Customer customer) {
        BeanUtils.copyProperties(customer, this);
    }

    public Customer toCustomer() {
        Customer customer = new Customer();
        BeanUtils.copyProperties(this, customer);
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
                '}';
    }
}