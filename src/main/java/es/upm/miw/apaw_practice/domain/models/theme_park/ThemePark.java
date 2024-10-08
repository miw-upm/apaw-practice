package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ThemePark {
    private String id;
    private LocalDateTime creationDate;
    private Boolean opened;
    private BigDecimal price;
    private List<Ride> rides;
    public ThemePark() {
        //empty for framework
    }

    public ThemePark(String id, LocalDateTime creationDate, Boolean opened, BigDecimal price, List<Ride> rides) {
        this.id = id;
        this.creationDate = creationDate;
        this.opened = opened;
        this.price = price;
        this.rides = rides;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ThemePark) obj).id));
    }

    @Override
    public String toString() {
        return "ThemeParkEntity{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", rides=" + rides +
                ", opened='" + opened + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
