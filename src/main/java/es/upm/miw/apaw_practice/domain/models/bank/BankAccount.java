package es.upm.miw.apaw_practice.domain.models.bank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankAccount {

    private String iban;
    private BigDecimal balance;
    private LocalDate openingDate;
    private boolean hasInterest;
    private Client client;

    public BankAccount() {
        //Empty for framework
    }

    public BankAccount(String iban, BigDecimal balance, LocalDate openingDate, boolean hasInterest, Client client) {
        this.iban = iban;
        this.balance = balance;
        this.openingDate = openingDate;
        this.hasInterest = hasInterest;
        this.client = client;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public boolean isHasInterest() {
        return hasInterest;
    }

    public void setHasInterest(boolean hasInterest) {
        this.hasInterest = hasInterest;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "IBAN='" + iban + '\'' +
                ", balance='" + balance + '\'' +
                ", openingDate=" + openingDate + '\'' +
                ", hasInterest=" + hasInterest + '\'' +
                ", client=" + client.getDni() +
                '}';
    }
}
