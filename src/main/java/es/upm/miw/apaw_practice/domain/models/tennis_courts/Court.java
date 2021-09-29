package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public class Court {
    private Integer number;
    private final BigDecimal PRICE;
    private Boolean occupied;

    public Court(Integer number, boolean occupied){
        this.number = number;
        this.PRICE = new BigDecimal(10.5);
        this.occupied = occupied;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice(){
        return this.PRICE;
    }

    public Boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
}
