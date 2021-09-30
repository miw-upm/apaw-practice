package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import java.math.BigDecimal;

public class EquipmentEntity {

    private String type;
    private Integer numberId;
    private BigDecimal pricePerUnit;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberId() {
        return this.numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public BigDecimal getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
