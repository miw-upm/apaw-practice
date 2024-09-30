package es.upm.miw.apaw_practice.domain.models.art_museum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Exhibition {
    private String name;
    private LocalDateTime dateOfExhibition;
    private BigDecimal price;
    private List<Artwork> artworks;

    public Exhibition() {
        //empty for framework
    }

    public Exhibition(String name, LocalDateTime dateOfExhibition, BigDecimal price, List<Artwork> artworks) {
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

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    @Override
    public String toString() {
        return "Exhibition{" +
                "name='" + name + '\'' +
                ", dateOfExhibition=" + dateOfExhibition +
                ", price=" + price +
                ", artworks=" + artworks +
                '}';
    }
}
