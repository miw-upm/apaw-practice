package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.math.BigDecimal;
import java.util.Objects;

public class Tyre {

    private String manufacturer;
    private BigDecimal price;
    private TyreSpecification specs;

    public Tyre() {
        //empty for framework
    }

    public Tyre(String manufacturer, BigDecimal price, TyreSpecification specs) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.specs = specs;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TyreSpecification getSpecs() {
        return specs;
    }

    public void setSpecs(TyreSpecification specs) {
        this.specs = specs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, specs);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() && (this.manufacturer.equals(((Tyre) o).manufacturer))
                && (this.specs.equals(((Tyre) o).specs));
    }

    @Override
    public String toString() {
        return "Tyre{" +
                "manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", specs=" + specs +
                '}';
    }
}
