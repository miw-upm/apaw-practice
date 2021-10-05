package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import java.math.BigDecimal;

public class EquipmentEntity {

    private String type;
    private Integer quantity;
    private BigDecimal pricePerUnit;

    public EquipmentEntity(String type, Integer quantity, BigDecimal pricePerUnit){
        this.type = type;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public EquipmentEntity(){
        //empty from framework
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
