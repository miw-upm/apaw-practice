package es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class BasketBallEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer ballId;
    private String brand;
    private BigDecimal price;
    @DBRef
    private BasketMatchEntity basketMatchEntity;

    public BasketBallEntity() {
        // empty for framework
    }

    public BasketBallEntity(Integer ballId, String brand, BigDecimal price, BasketMatchEntity basketMatchEntity) {
        this.id = UUID.randomUUID().toString();
        this.ballId = ballId;
        this.brand = brand;
        this.price = price;
        this.basketMatchEntity = basketMatchEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBallId() {
        return ballId;
    }

    public void setBallId(Integer ballId) {
        this.ballId = ballId;
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

    public BasketMatchEntity getBasketMatchEntity() {
        return basketMatchEntity;
    }

    public void setBasketMatchEntity(BasketMatchEntity basketMatchEntity) {
        this.basketMatchEntity = basketMatchEntity;
    }

    public BasketBall toBasketBall() {
        return new BasketBall(ballId,brand,price,basketMatchEntity.toBasketMatch());
    }

    @Override
    public int hashCode() {
        return ballId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && ballId.equals(((BasketBallEntity) obj).ballId));
    }

    @Override
    public String toString() {
        return "BasketBallEntity{" +
                "id='" + id + '\'' +
                ", ballId=" + ballId +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", basketMatchEntity=" + basketMatchEntity +
                '}';
    }
}