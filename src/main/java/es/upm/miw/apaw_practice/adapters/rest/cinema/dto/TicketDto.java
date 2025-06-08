package es.upm.miw.apaw_practice.adapters.rest.cinema.dto;

import java.math.BigDecimal;

public class TicketDto {
    private BigDecimal ticketPrice;
    private String purchaseDate; // ISO, ej. "2025-06-08T17:30:00"
    private Boolean vip;
    private BigDecimal totalPrice;

    public TicketDto() {}

    public TicketDto(BigDecimal ticketPrice, String purchaseDate, Boolean vip, BigDecimal totalPrice) {
        this.ticketPrice = ticketPrice;
        this.purchaseDate = purchaseDate;
        this.vip = vip;
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(BigDecimal ticketPrice) { this.ticketPrice = ticketPrice; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public Boolean getVip() { return vip; }
    public void setVip(Boolean vip) { this.vip = vip; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}