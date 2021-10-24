package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.math.BigDecimal;

public class Equipment {

    private String type;
    private Integer quantity;
    private BigDecimal pricePerUnit;

    public Equipment(){
        //empty for framework
    }

    public static EquipmentBuilders.Type builder(){
        return new Builder();
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

    private static class Builder implements EquipmentBuilders.Type, EquipmentBuilders.Quantity, EquipmentBuilders.PricePerUnit, EquipmentBuilders{
        private final Equipment equipment;

        public Builder(){
            this.equipment = new Equipment();
        }

        @Override
        public Quantity type(String type) {
            this.equipment.type = type;
            return this;
        }

        @Override
        public PricePerUnit quantity(Integer quantity) {
            this.equipment.quantity = quantity;
            return this;
        }

        @Override
        public EquipmentBuilders pricePerUnit(BigDecimal pricePerUnit) {
            this.equipment.pricePerUnit = pricePerUnit;
            return this;
        }

        @Override
        public Equipment build() {
            return this.equipment;
        }
    }


}
