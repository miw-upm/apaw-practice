package es.upm.miw.apaw_practice.domain.models.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseBill {
    private int id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    private Company company;

    // Constructor, getters, and setters
    public ExpenseBill(int id, String description, BigDecimal amount, LocalDateTime date, Company company) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

