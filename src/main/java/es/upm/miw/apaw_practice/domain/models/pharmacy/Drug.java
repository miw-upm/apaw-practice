package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.math.BigDecimal;
import java.util.Objects;

public class Drug {

    private String barcode;
    private String name;
    private Boolean commercialized;
    private BigDecimal price;

    public Drug() {
        //empty for framework
    }

    public static DrugBuilders.Barcode builder() {
        return new Builder();
    }

    public Drug(String barcode, String name, Boolean commercialized, BigDecimal price) {
        this.barcode = barcode;
        this.name = name;
        this.commercialized = commercialized;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCommercialized() {
        return commercialized;
    }

    public void setCommercialized(Boolean commercialized) {
        this.commercialized = commercialized;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class Builder implements DrugBuilders.Barcode, DrugBuilders.Name, DrugBuilders.Commercialized, DrugBuilders.Price, DrugBuilders.Optionals {

        private final Drug drug;

        public Builder() {
            this.drug = new Drug();
        }

        @Override
        public DrugBuilders.Name barcode(String barcode) {
            this.drug.barcode = barcode;
            return this;
        }

        @Override
        public DrugBuilders.Commercialized name(String name) {
            this.drug.name = name;
            return this;
        }

        @Override
        public DrugBuilders.Price commercialized(Boolean commercialized) {
            this.drug.commercialized = commercialized;
            return this;
        }

        @Override
        public DrugBuilders.Optionals price(BigDecimal price) {
            this.drug.price = price;
            return this;
        }


        @Override
        public Drug build() {
            return this.drug;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return barcode.equals(drug.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }

    @Override
    public String toString() {
        return "Drug{" +
                "barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", commercialized=" + commercialized +
                ", price=" + price +
                '}';
    }
}
