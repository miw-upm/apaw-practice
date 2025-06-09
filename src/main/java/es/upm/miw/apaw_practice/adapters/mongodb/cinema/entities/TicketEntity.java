package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "tickets")
public class TicketEntity {
    @Id
    private String id;
    private String screeningId;
    private String seat;
    private BigDecimal price;

    private LocalDateTime purchaseDate;
    private Boolean vip;
    private BigDecimal totalPrice;

    public TicketEntity() {}

    public TicketEntity(String screeningId, String seat, BigDecimal price, LocalDateTime purchaseDate, Boolean vip, BigDecimal totalPrice) {
        this.screeningId = screeningId;
        this.seat = seat;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.vip = vip;
        this.totalPrice = totalPrice;
    }

    public static TicketEntity fromTicket(Ticket ticket) {
        TicketEntity entity = new TicketEntity();
        BeanUtils.copyProperties(ticket, entity);
        entity.screeningId = ticket.getScreeningId();
        return entity;
    }

    public Ticket toTicket() {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(this, ticket);
        ticket.setScreeningId(this.screeningId);
        return ticket;
    }

    public void updateFromTicket(Ticket ticket) {
        this.screeningId = ticket.getScreeningId();
        this.seat = ticket.getSeat();
        this.price = ticket.getPrice();
        this.purchaseDate = ticket.getPurchaseDate();
        this.vip = ticket.getVip();
        this.totalPrice = ticket.getTotalPrice();
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getScreeningId() { return screeningId; }
    public void setScreeningId(String screeningId) { this.screeningId = screeningId; }
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate = purchaseDate; }
    public Boolean getVip() { return vip; }
    public void setVip(Boolean vip) { this.vip = vip; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}