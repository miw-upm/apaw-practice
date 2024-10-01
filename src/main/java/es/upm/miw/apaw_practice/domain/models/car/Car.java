package es.upm.miw.apaw_practice.domain.models.car;

import java.math.BigDecimal;

public class Car {

    private String name;
    private boolean isElectric;

    private BigDecimal price;

    public Car() {
        //empty for framework
    }
    public Car(String name, boolean isElectric, BigDecimal price) {
        this.name = name;
        this.isElectric = isElectric;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", isElectric=" + isElectric +
                ", price=" + price +
                '}';
    }
}
