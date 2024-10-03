package es.upm.miw.apaw_practice.domain.models.theme_park;

import es.upm.miw.apaw_practice.domain.models.shop.ArticleItem;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ThemePark {
    private String id;
    private String name;
    private LocalDateTime creationDate;
    private Boolean isOpen;
    private BigDecimal price;
    private List<Ride> rides;
    public ThemePark() {
        //empty for framework
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
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
                ", name='" + name + '\'' +
                ", isOpen='" + isOpen + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
