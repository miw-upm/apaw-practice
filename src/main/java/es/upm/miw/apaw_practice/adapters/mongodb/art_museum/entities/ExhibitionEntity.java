package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities;

import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.models.art_museum.Exhibition;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExhibitionEntity {
    private String name;
    private LocalDateTime dateOfExhibition;
    private BigDecimal price;

    public ExhibitionEntity() {
        //empty for framework
    }

    public ExhibitionEntity(String name, LocalDateTime dateOfExhibition, BigDecimal price) {
        this.name = name;
        this.dateOfExhibition = dateOfExhibition;
        this.price = price;
    }

    public Exhibition toExhibition() {
        Exhibition exhibition = new Exhibition();
        BeanUtils.copyProperties(this, exhibition);
        return exhibition;
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

    @Override
    public String toString() {
        return "ExhibitionEntity{" +
                "name='" + name + '\'' +
                ", dateOfExhibition=" + dateOfExhibition +
                ", price=" + price +
                '}';
    }
}
