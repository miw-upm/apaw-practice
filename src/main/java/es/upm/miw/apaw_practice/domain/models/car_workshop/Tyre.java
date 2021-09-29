package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.math.BigDecimal;
import java.util.Objects;

public class Tyre {

    private String manufacturer;
    private String model;
    private BigDecimal price;

    public Tyre() {
        //empty for framework
    }

    public Tyre(String manufacturer, String model, BigDecimal price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.manufacturer, this.model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.manufacturer, ((Tyre) o).manufacturer) &&
                Objects.equals(this.model, ((Tyre) o).model);
    }

    @Override
    public String toString() {
        return "Tyre{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
