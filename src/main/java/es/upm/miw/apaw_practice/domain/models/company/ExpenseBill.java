package es.upm.miw.apaw_practice.domain.models.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseBill {
    private int expenseBill_id;// Primary Key
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;


    public ExpenseBill() {
        //empty for framework
    }
    public ExpenseBill(int expenseBill_id, String description, BigDecimal amount, LocalDateTime date) {
        this.expenseBill_id = expenseBill_id;
        this.description = description;
        this.amount = amount;
        this.date = date;

    }

    public int getExpenseBill_id() {
        return expenseBill_id;
    }

    public void setExpenseBill_id(int expenseBill_id) {
        this.expenseBill_id = expenseBill_id;
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



}

