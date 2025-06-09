package es.upm.miw.apaw_practice.domain.models.cinema;

import java.math.BigDecimal;

public class Ticket {
    private String id;
    private String screeningId;
    private BigDecimal price;
    private String seat; // <--- Añadido

    public Ticket() {}

    public Ticket(String id, String screeningId, BigDecimal price, String seat) {
        this.id = id;
        this.screeningId = screeningId;
        this.price = price;
        this.seat = seat;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getScreeningId() { return screeningId; }
    public void setScreeningId(String screeningId) { this.screeningId = screeningId; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getSeat() { return seat; } // <--- Añadido
    public void setSeat(String seat) { this.seat = seat; } // <--- Añadido
}