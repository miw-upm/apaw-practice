package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Document
public class ProductEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String productName;

    private String name;
    private Integer numberProduct;
    private BigDecimal unitPrice;

    public ProductEntity() {
        //Empty for framework
    }

    public ProductEntity(String productName, String name, Integer numberProduct, BigDecimal unitPrice) {
        this.id = UUID.randomUUID().toString();  // 使用UUID生成唯一标识符
        this.productName = productName;
        this.name = name;
        this.numberProduct = numberProduct;
        this.unitPrice = unitPrice;
    }

    // Getters and Setters
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // Equals and HashCode based on `productName` for uniqueness
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    // ToString method
    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", name='" + name + '\'' +
                ", numberProduct=" + numberProduct +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
