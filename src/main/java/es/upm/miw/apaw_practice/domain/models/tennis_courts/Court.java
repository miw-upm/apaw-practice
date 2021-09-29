package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public class Court {
    private Integer number;
    private BigDecimal price;
    private Boolean occupied;

    public Court(Integer number, boolean occupied, BigDecimal price){
        this.number = number;
        this.price = price;
        this.occupied = occupied;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
