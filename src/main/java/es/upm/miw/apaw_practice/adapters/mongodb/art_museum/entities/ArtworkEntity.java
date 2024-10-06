package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ArtworkEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String inventoryNumber;
    private String titleName;
    private Integer year;

    public ArtworkEntity() {
        //empty from framework
    }

    public ArtworkEntity(Artwork artwork) {
        BeanUtils.copyProperties(artwork, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (inventoryNumber.equals(((ArtworkEntity) obj).inventoryNumber));
    }

    @Override
    public int hashCode() {
        return inventoryNumber.hashCode();
    }

    @Override
    public String toString() {
        return "ArtworkEntity{" +
                "id='" + id + '\'' +
                ", inventoryNumber='" + inventoryNumber + '\'' +
                ", titleName='" + titleName + '\'' +
                ", year=" + year +
                '}';
    }
}
