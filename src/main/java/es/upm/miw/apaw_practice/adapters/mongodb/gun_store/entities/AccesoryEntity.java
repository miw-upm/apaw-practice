package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities;

import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class AccesoryEntity {
    @Id
    private Integer accesoryId;
    private String category;
    private BigDecimal price;

    public AccesoryEntity() {
        // Empty for Framework
    }

    public AccesoryEntity(Accesory accesory) {
        BeanUtils.copyProperties(accesory, this);
    }

    public Integer getAccesoryId() {
        return accesoryId;
    }

    public void setAccesoryId(Integer accesoryId) {
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

    public AccesoryEntity fromAccesory(Accesory accesory) {
        BeanUtils.copyProperties(accesory, this);
        return this;
    }

    public Accesory toAccesory() {
        Accesory accesory = new Accesory();
        BeanUtils.copyProperties(this, accesory);
        return accesory;
    }

    @Override
    public String toString() {
        return "AccesoryEntity{" +
                "accesoryId=" + accesoryId +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
