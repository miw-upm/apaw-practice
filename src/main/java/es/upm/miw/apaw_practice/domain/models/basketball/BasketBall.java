package es.upm.miw.apaw_practice.domain.models.basketball;

import java.math.BigDecimal;

public class BasketBall {

    int id;
    String brand;
    BigDecimal price;
    BasketMatch match;

    public BasketBall(int id, String brand, BigDecimal price, BasketMatch match) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.match = match;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BasketMatch getMatch() {
        return match;
    }

    public void setMatch(BasketMatch match) {
        this.match = match;
    }
}
