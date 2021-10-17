package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public class Equipment {

    private String type;
    private Integer quantity;
    private BigDecimal pricePerUnit;

    public Equipment(){
        //empty for framework
    }

    public Equipment(String type, Integer quantity, BigDecimal pricePerUnit){
        this.type = type;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getTotalPrice(){
        return this.pricePerUnit.multiply(new BigDecimal(quantity));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPricePerUnit(BigDecimal price){
        this.pricePerUnit = price;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "type='" + this.type + '\'' +
                ", number=" + this.quantity +
                ", pricePerUnit=" + this.pricePerUnit +
                '}';
    }
}
