package es.upm.miw.apaw_practice.domain.models.art_museum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Exhibition {
    private String name;
    private LocalDateTime dateOfExhibition;
    private BigDecimal price;

    public Exhibition() {
        //empty for framework
    }

    public Exhibition(String name, LocalDateTime dateOfExhibition, BigDecimal price) {
        this.name = name;
        this.dateOfExhibition = dateOfExhibition;
        this.price = price;
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
        return "Exhibition{" +
                "name='" + name + '\'' +
                ", dateOfExhibition=" + dateOfExhibition +
                ", price=" + price +
                '}';
    }
}
