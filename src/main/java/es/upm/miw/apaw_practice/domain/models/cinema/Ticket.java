package es.upm.miw.apaw_practice.domain.models.cinema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private String screeningId;
    private BigDecimal price;
    private String seat;
    private LocalDateTime purchaseDate;
    private Boolean vip;
    private BigDecimal totalPrice;

    public Ticket() {}

    // Constructor largo
    public Ticket(String id, String screeningId, BigDecimal price, String seat,
                  LocalDateTime purchaseDate, Boolean vip, BigDecimal totalPrice) {
        this.id = id;
        this.screeningId = screeningId;
        this.price = price;
        this.seat = seat;
        this.purchaseDate = purchaseDate;
        this.vip = vip;
        this.totalPrice = totalPrice;
    }

    // Constructor que espera el test
    public Ticket(String id, String screeningId, BigDecimal price, String seat) {
        this(id, screeningId, price, seat, null, null, null);
    }

    // Getters y setters (todos los campos)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getScreeningId() { return screeningId; }
    public void setScreeningId(String screeningId) { this.screeningId = screeningId; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate = purchaseDate; }
    public Boolean getVip() { return vip; }
    public void setVip(Boolean vip) { this.vip = vip; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}