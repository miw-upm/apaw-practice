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
}
