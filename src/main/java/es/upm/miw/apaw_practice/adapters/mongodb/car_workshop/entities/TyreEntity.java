package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Document
public class TyreEntity {
    @Id
    private String id;
    private String manufacturer;
    private String model;
    private BigDecimal price;
    private List<TyreSpecificationEntity> tyreSpecsEntities;

    public TyreEntity() {
        //empty for framework
    }

    public TyreEntity(String manufacturer, String model, BigDecimal price,
                      List<TyreSpecificationEntity> tyreSpecsEntities) {
        this.id = UUID.randomUUID().toString();
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.tyreSpecsEntities = tyreSpecsEntities;
    }

    public String getId() {
        return id;
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

    public List<TyreSpecificationEntity> getTyreSpecsEntities() {
        return tyreSpecsEntities;
    }

    public void setTyreSpecsEntities(List<TyreSpecificationEntity> tyreSpecsEntities) {
        this.tyreSpecsEntities = tyreSpecsEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.manufacturer, this.model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.manufacturer, ((TyreEntity) o).manufacturer) &&
                Objects.equals(this.model, ((TyreEntity) o).model);
    }

    @Override
    public String toString() {
        return "TyreEntity{" +
                "id='" + this.id + '\'' +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", model='" + this.model + '\'' +
                ", price=" + this.price +
                ", tyreSpecsEntities=" + this.tyreSpecsEntities.toString() +
                '}';
    }
}
