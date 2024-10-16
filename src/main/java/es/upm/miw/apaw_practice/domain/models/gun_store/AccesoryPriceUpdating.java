package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;

public class AccesoryPriceUpdating {

    private Integer accesoryId;
    private BigDecimal newPrice;

    public AccesoryPriceUpdating() {
        //Empty for framework
    }

    public AccesoryPriceUpdating(Integer accesoryId, BigDecimal newPrice) {
        this.accesoryId = accesoryId;
        this.newPrice = newPrice;
    }

    public Integer getAccesoryId() {
        return accesoryId;
    }

    public void setAccesoryId(Integer accesoryId) {
        this.accesoryId = accesoryId;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return "AccesoryPriceUpdating{" +
                "accesoryId=" + accesoryId +
                ", newPrice=" + newPrice +
                '}';
    }
}
