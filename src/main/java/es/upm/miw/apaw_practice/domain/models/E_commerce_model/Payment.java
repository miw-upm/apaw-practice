package es.upm.miw.apaw_practice.domain.models.E_commerce_model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private LocalDateTime paymentDate;
    private BigDecimal amount;
    private Boolean isSuccessful;

    // Constructor
    public Payment(Long id, LocalDateTime paymentDate, BigDecimal amount, Boolean isSuccessful) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.isSuccessful = isSuccessful;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", isSuccessful=" + isSuccessful +
                '}';
    }
}

