package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.math.BigDecimal;
import java.util.Objects;

public class Tyre {

    private String manufacturer;
    private BigDecimal price;
    private TyreSpecification tyreSpec;

    public Tyre() {
        //empty for framework
    }

    public Tyre(String manufacturer, BigDecimal price, TyreSpecification specs) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.tyreSpec = specs;
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

    public TyreSpecification getTyreSpec() {
        return tyreSpec;
    }

    public void setTyreSpec(TyreSpecification tyreSpec) {
        this.tyreSpec = tyreSpec;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.manufacturer, this.tyreSpec);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.manufacturer, ((Tyre) o).manufacturer) &&
                Objects.equals(this.tyreSpec, ((Tyre) o).tyreSpec);
    }

    @Override
    public String toString() {
        return "Tyre{" +
                "manufacturer='" + this.manufacturer + '\'' +
                ", price=" + this.price +
                ", specs=" + this.tyreSpec.toString() +
                '}';
    }
}
