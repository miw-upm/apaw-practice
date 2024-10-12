package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    @DBRef
    private ArtistEntity artist;

    public ArtworkEntity() {
        //empty from framework
    }

    public ArtworkEntity(String inventoryNumber, String titleName, Integer year, ArtistEntity artist) {
        this.id = UUID.randomUUID().toString();
        this.inventoryNumber = inventoryNumber;
        this.titleName = titleName;
        this.year = year;
        this.artist = artist;
    }

    public Artwork toArtwork() {
        Artwork artwork = new Artwork();
        BeanUtils.copyProperties(this, artwork);
        if (this.artist != null) {
            artwork.setArtist(this.artist.toArtist());
        }
        return artwork;
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

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
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
                ", artist=" + artist +
                '}';
    }
}
