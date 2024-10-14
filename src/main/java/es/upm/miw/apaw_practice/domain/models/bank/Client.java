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

    public static ClientBuilders.Dni builder() {
        return new Builder();
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

    public static class Builder implements ClientBuilders.Dni, ClientBuilders.Name, ClientBuilders.Surname,
            ClientBuilders.PhoneNumber, ClientBuilders.Email, ClientBuilders.Optionals {

        private final Client client;

        public Builder() {
            this.client = new Client();
        }

        @Override
        public ClientBuilders.Name dni(String dni) {
            this.client.dni = dni;
            return this;
        }

        @Override
        public ClientBuilders.Surname name(String name) {
            this.client.name = name;
            return this;
        }

        @Override
        public ClientBuilders.PhoneNumber surname(String surname) {
            this.client.surname = surname;
            return this;
        }

        @Override
        public ClientBuilders.Email phoneNumber(Integer phoneNumber) {
            this.client.phoneNumber = phoneNumber;
            return this;
        }

        @Override
        public ClientBuilders.Optionals email(String email) {
            this.client.email = email;
            return this;
        }

        @Override
        public ClientBuilders.Optionals investmentFunds(List<InvestmentFund> investmentFunds) {
            this.client.investmentFunds = investmentFunds;
            return this;
        }

        @Override
        public Client build() {
            return this.client;
        }
    }
}
