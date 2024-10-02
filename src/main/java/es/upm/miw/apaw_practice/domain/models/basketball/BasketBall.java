package es.upm.miw.apaw_practice.domain.models.basketball;

import java.math.BigDecimal;

public class BasketBall {

    private int id;
    private String brand;
    private BigDecimal price;

    public BasketBall(int id, String brand, BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.price = price;
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
}
