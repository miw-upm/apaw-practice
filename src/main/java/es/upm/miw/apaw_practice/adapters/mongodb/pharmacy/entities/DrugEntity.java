package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Document
public class DrugEntity {

    @Id
    private String barcode;
    private String name;
    private Boolean commercialized;
    private BigDecimal price;

    public DrugEntity() {
        //empty from framework
    }

    public DrugEntity(Drug drug) {
        BeanUtils.copyProperties(drug, this);
    }

    public DrugEntity(String barcode, String name, Boolean commercialized, BigDecimal price) {
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

    public Drug toDrug() {
        Drug drug = new Drug();
        BeanUtils.copyProperties(this, drug);
        return drug;
    }

    public void fromDrug(Drug drug) {
        BeanUtils.copyProperties(drug, this);
    }

}
