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

    public static ExhibitionBuilders.Name builder() {
        return new Builder();
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

    public static class Builder implements ExhibitionBuilders.Name, ExhibitionBuilders.DateOfExhibition, ExhibitionBuilders.Optionals {
        private final Exhibition exhibition;

        public Builder() {
            this.exhibition = new Exhibition();
        }

        @Override
        public ExhibitionBuilders.DateOfExhibition name(String name) {
            this.exhibition.name = name;
            return this;
        }

        @Override
        public ExhibitionBuilders.Optionals dateOfExhibition(LocalDateTime dateOfExhibition) {
            this.exhibition.dateOfExhibition = dateOfExhibition;
            return this;
        }

        @Override
        public ExhibitionBuilders.Optionals price(BigDecimal price) {
            this.exhibition.price = price;
            return this;
        }

        @Override
        public Exhibition build() {
            return this.exhibition;
        }
    }
}
