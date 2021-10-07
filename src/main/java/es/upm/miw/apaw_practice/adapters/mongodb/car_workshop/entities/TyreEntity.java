package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    @DBRef
    private TyreSpecificationEntity tyreSpecsEntity;

    public TyreEntity() {
        //empty for framework
    }

    public TyreEntity(String manufacturer, String model, BigDecimal price,
                      TyreSpecificationEntity tyreSpecsEntity) {
        this.id = UUID.randomUUID().toString();
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.tyreSpecsEntity = tyreSpecsEntity;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TyreSpecificationEntity getTyreSpecsEntity() {
        return this.tyreSpecsEntity;
    }

    public void setTyreSpecsEntity(TyreSpecificationEntity tyreSpecsEntity) {
        this.tyreSpecsEntity = tyreSpecsEntity;
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
                ", tyreSpecsEntities=" + this.tyreSpecsEntity.toString() +
                '}';
    }
}
