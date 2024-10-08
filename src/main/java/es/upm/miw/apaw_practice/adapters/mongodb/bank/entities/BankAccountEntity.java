package es.upm.miw.apaw_practice.adapters.mongodb.bank.entities;

import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Document
public class BankAccountEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String iban;
    private BigDecimal balance;
    private LocalDate openingDate;
    private Boolean hasInterest;
    @DBRef
    private ClientEntity clientEntity;

    public BankAccountEntity() {
        //Empty for framework
    }

    public BankAccountEntity(String iban, BigDecimal balance, LocalDate openingDate, Boolean hasInterest, ClientEntity clientEntity) {
        this.id = UUID.randomUUID().toString();
        this.iban = iban;
        this.balance = balance;
        this.openingDate = openingDate;
        this.hasInterest = hasInterest;
        this.clientEntity = clientEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setHasInterest(Boolean hasInterest) {
        this.hasInterest = hasInterest;
    }

    public ClientEntity getClient() {
        return clientEntity;
    }

    public void setClient(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public BankAccount toBankAccount() {
        return new BankAccount(iban, balance, openingDate, hasInterest, clientEntity.toClient());
    }

    @Override
    public int hashCode() {
        return iban.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (iban.equals(((BankAccountEntity) obj).iban));
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "IBAN='" + iban + '\'' +
                ", balance='" + balance + '\'' +
                ", openingDate=" + openingDate + '\'' +
                ", hasInterest=" + hasInterest + '\'' +
                ", client=" + clientEntity.getDni() +
                '}';
    }
}
