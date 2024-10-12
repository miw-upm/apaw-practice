package es.upm.miw.apaw_practice.domain.models.e_commerce_model;

import java.math.BigDecimal;

public class Product {

    private String productName;
    private Integer numberProduct;
    private BigDecimal unitPrice;

    public Product() {
        // Empty for framework
    }

    public Product(String productName, Integer numberProduct, BigDecimal unitPrice) {
        this.productName = productName;
        this.numberProduct = numberProduct;
        this.unitPrice = unitPrice;
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
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", numberProduct=" + numberProduct +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
