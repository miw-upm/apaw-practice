package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public class Equipment {

    private String type;
    private Integer numberId;
    private BigDecimal pricePerUnit;

    public Equipment(String type, Integer numberId, BigDecimal pricePerUnit){
        this.type = type;
        this.numberId = numberId;
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getTotalPrice(){
        return this.pricePerUnit.multiply(new BigDecimal(numberId));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
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
                ", number=" + this.numberId +
                ", pricePerUnit=" + this.pricePerUnit +
                '}';
    }
}
