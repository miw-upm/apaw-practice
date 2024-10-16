package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Document
public class ProductECommerceEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String productName;
    private Integer numberProduct;
    private BigDecimal unitPrice;

    public ProductECommerceEntity() {
        //Empty for framework
    }

    public ProductECommerceEntity(String productName, Integer numberProduct, BigDecimal unitPrice) {
        this.id = UUID.randomUUID().toString();
        this.productName = productName;
        this.numberProduct = numberProduct;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(Integer numberProduct) {
        this.numberProduct = numberProduct;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductECommerceEntity that = (ProductECommerceEntity) o;
        return Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", numberProduct=" + numberProduct +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
