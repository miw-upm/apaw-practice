package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.math.BigDecimal;
import java.util.List;

public class Drug {

    private String barcode;
    private String name;
    private List<ActiveIngredient> activeIngredient;
    private Boolean commercialized;
    private BigDecimal price;

    public Drug(String name, List<ActiveIngredient> activeIngredient, Boolean commercialized, BigDecimal price) {
        this.name = name;
        this.activeIngredient = activeIngredient;
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

    public List<ActiveIngredient> getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(List<ActiveIngredient> activeIngredient) {
        this.activeIngredient = activeIngredient;
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
