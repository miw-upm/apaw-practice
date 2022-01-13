package es.upm.miw.apaw_practice.domain.models.training;

import java.math.BigDecimal;

public class CoursePriceUpdating {
    private String identity;
    private BigDecimal price;

    public CoursePriceUpdating() {
        //empty from framework
    }

    public CoursePriceUpdating(String identity, BigDecimal price) {
        this.identity = identity;
        this.price = price;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CoursePriceUpdating{" +
                "identity='" + identity + '\'' +
                ", price=" + price +
                '}';
    }
}
