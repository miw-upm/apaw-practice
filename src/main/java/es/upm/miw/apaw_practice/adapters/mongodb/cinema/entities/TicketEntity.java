package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketEntity {
    private BigDecimal ticketPrice;
    private LocalDateTime purchaseDate;
    private Boolean vip;
    private BigDecimal totalPrice;

    public TicketEntity() {}

    public TicketEntity(BigDecimal ticketPrice, LocalDateTime purchaseDate, Boolean vip, BigDecimal totalPrice) {
        this.ticketPrice = ticketPrice;
        this.purchaseDate = purchaseDate;
        this.vip = vip;
        this.totalPrice = totalPrice;
    }

    // Getters and setters

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}