package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;

import java.math.BigDecimal;
import java.util.Objects;

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

    public BigDecimal getTotalPrice(){
        return this.pricePerUnit.multiply(BigDecimal.valueOf(this.quantity));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentEntity that = (EquipmentEntity) o;
        return this.type.equals(that.type) && this.quantity.equals(that.quantity) && this.pricePerUnit.equals(that.pricePerUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, quantity, pricePerUnit);
    }
}
