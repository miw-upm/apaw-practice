package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ExhibitionEntity {
    private String name;
    private LocalDateTime dateOfExhibition;
    private BigDecimal price;
    @DBRef
    private List<ArtworkEntity> artworks;

    public ExhibitionEntity() {
        //empty for framework
    }

    public ExhibitionEntity(String name, LocalDateTime dateOfExhibition, BigDecimal price, List<ArtworkEntity> artworks) {
        this.name = name;
        this.dateOfExhibition = dateOfExhibition;
        this.price = price;
        this.artworks = artworks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateOfExhibition() {
        return dateOfExhibition;
    }

    public void setDateOfExhibition(LocalDateTime dateOfExhibition) {
        this.dateOfExhibition = dateOfExhibition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ArtworkEntity> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkEntity> artworks) {
        this.artworks = artworks;
    }

    @Override
    public String toString() {
        return "ExhibitionEntity{" +
                "name='" + name + '\'' +
                ", dateOfExhibition=" + dateOfExhibition +
                ", price=" + price +
                ", artworks=" + artworks +
                '}';
    }
}
