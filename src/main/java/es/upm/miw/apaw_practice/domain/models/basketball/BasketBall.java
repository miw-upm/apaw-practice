package es.upm.miw.apaw_practice.domain.models.basketball;

import java.math.BigDecimal;
import java.util.List;

public class BasketBall {

    private int id;
    private String brand;
    private BigDecimal price;
    private List<BasketMatch> basketMatchList;

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

    public List<BasketMatch> getBasketMatchList() {
        return basketMatchList;
    }

    public void setBasketMatchList(List<BasketMatch> basketMatchList) {
        this.basketMatchList = basketMatchList;
    }
}
