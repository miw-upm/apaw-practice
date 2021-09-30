package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public class Equipment {

    private String type;
    private Integer number;
    private BigDecimal pricePerUnit;

    public Equipment(String type, Integer number){
        this.type = type;
        this.number = number;
        this.pricePerUnit = new BigDecimal(5.0);
    }

    public BigDecimal getTotalPrice(){
        return this.pricePerUnit.multiply(new BigDecimal(number));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPricePerUnit(BigDecimal price){
        this.pricePerUnit = price;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }
}