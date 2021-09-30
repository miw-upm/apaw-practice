package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import java.math.BigDecimal;

public class EquipmentEntity {

    private String type;
    private Integer number;
    private BigDecimal pricePerUnit;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
