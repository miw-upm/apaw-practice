package es.upm.miw.apaw_practice.domain.models.bank;

import java.util.List;

public class Client {

    private String dni;
    private String name;
    private String surname;
    private Integer phoneNumber;
    private String email;
    private List<InvestmentFund> investmentFunds;

    public Client() {
        // Empty for framework
    }

    public Client(String dni, String name, String surname, Integer phoneNumber, String email, List<InvestmentFund> investmentFunds) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.investmentFunds = investmentFunds;
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
