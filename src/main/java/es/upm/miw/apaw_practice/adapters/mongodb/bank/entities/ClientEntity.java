package es.upm.miw.apaw_practice.adapters.mongodb.bank.entities;

import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.models.bank.InvestmentFund;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class ClientEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String name;
    private String surname;
    private Integer phoneNumber;
    private String email;
    @DBRef
    private List<InvestmentFund> investmentFunds;

    public ClientEntity() {
        // Empty for framework
    }

    public ClientEntity(Client client) {
        BeanUtils.copyProperties(client, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<InvestmentFund> getInvestmentFunds() {
        return investmentFunds;
    }

    public void setInvestmentFunds(List<InvestmentFund> investmentFunds) {
        this.investmentFunds = investmentFunds;
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (dni.equals(((ClientEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "Client{" +
                "DNI='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname=" + surname + '\'' +
                ", phone=" + phoneNumber + '\'' +
                ", email=" + email + '\'' +
                ", investmentFunds=" + investmentFunds.stream().map(InvestmentFund::getName).toList() +
                '}';
    }

}
