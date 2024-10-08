package es.upm.miw.apaw_practice.adapters.mongodb.car.entities;

import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.beans.BeanUtils;
import es.upm.miw.apaw_practice.domain.models.car.Piece;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class PieceEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String partNumber;
    private String description;
    private BigDecimal cost;

    @DBRef
    private List<ManufacturerEntity> manufacturerListEntity;

    public PieceEntity() {
        //empty for framework
    }


    public PieceEntity(String partNumber, String description, BigDecimal cost, List<ManufacturerEntity> manufacturerListEntity){
        this.id = UUID.randomUUID().toString();
        this.partNumber = partNumber;
        this.description = description;
        this.cost = cost;
        this.manufacturerListEntity = manufacturerListEntity;
    }
    public Piece toPiece(){
        Piece piece = new Piece();
        BeanUtils.copyProperties(this, piece);
        piece.setManufacturerList(this.manufacturerListEntity.stream().map(ManufacturerEntity::toManufacturer).toList());
        return piece;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<ManufacturerEntity> getManufacturerListEntity() {
        return manufacturerListEntity;
    }

    public void setManufacturerListEntity(List<ManufacturerEntity> manufacturerListEntity) {
        this.manufacturerListEntity = manufacturerListEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceEntity that = (PieceEntity) o;
        return Objects.equals(partNumber, that.partNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber);
    }

    @Override
    public String toString() {
        return "PieceEntity{" +
                "id='" + id + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", manufacturerList=" + manufacturerListEntity +
                '}';
    }
}
