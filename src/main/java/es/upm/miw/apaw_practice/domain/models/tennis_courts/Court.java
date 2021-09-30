package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public class Court {
    private Integer numberId;
    private BigDecimal price;
    private Boolean occupied;

    public Court(Integer numberId, boolean occupied, BigDecimal price){
        this.numberId = numberId;
        this.price = price;
        this.occupied = occupied;
    }

    public Integer getNumberId() {
        return this.numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public Boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

}
