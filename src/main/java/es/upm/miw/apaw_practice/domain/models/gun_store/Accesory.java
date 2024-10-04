package es.upm.miw.apaw_practice.domain.models.gun_store;

import java.math.BigDecimal;

public class Accesory {
    private Integer accesoryId;
    private String category;
    private BigDecimal price;

    public Accesory() {
        //Empty for framework
    }

    public Accesory(int accesoryId, String category, BigDecimal price) {
        this.accesoryId = accesoryId;
        this.category = category;
        this.price = price;
    }

    public int getAccesoryId() {
        return accesoryId;
    }

    public void setAccesoryId(int accesoryId) {
        this.accesoryId = accesoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
