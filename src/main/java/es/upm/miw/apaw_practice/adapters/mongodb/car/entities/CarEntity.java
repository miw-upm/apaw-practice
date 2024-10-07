package es.upm.miw.apaw_practice.adapters.mongodb.car.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class CarEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String model;
    private boolean isElectric;
    private BigDecimal price;

    @DBRef
    private OwnerCarEntity ownerCarEntity;

    @DBRef
    private List<PieceEntity> piecesEntity;

    public CarEntity() {
        //empty for framework
    }


    public CarEntity(String model, boolean isElectric, BigDecimal price, OwnerCarEntity ownerCarEntity, List<PieceEntity> piecesEntity){
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.isElectric = isElectric;
        this.price = price;
        this.ownerCarEntity = ownerCarEntity;
        this.piecesEntity = piecesEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public OwnerCarEntity getOwnerEntity() {
        return ownerCarEntity;
    }

    public void setOwnerEntity(OwnerCarEntity ownerCarEntity) {
        this.ownerCarEntity = ownerCarEntity;
    }

    public List<PieceEntity> getPiecesEntity() {
        return piecesEntity;
    }

    public void setPiecesEntity(List<PieceEntity> piecesEntity) {
        this.piecesEntity = piecesEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(model, carEntity.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", isElectric=" + isElectric +
                ", price=" + price +
                ", ownerEntity=" + ownerCarEntity +
                ", piecesEntity=" + piecesEntity +
                '}';
    }
}
