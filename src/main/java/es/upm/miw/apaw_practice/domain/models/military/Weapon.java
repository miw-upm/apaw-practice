package es.upm.miw.apaw_practice.domain.models.military;

import java.math.BigDecimal;

public class Weapon {
    private String serialCode;
    private String manufacturer;
    private BigDecimal cost;

    public Weapon() {
        //empty for framework
    }

    public Weapon(String serialCode, String manufacturer, BigDecimal cost) {
        this.serialCode = serialCode;
        this.manufacturer = manufacturer;
        this.cost = cost;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return this.serialCode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (serialCode.equals(((Weapon) obj).serialCode));
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "serialCode='" + serialCode + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", cost=" + cost +
                '}';
    }
}
