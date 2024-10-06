package es.upm.miw.apaw_practice.domain.models.E_commerce_model;

import java.math.BigDecimal;

public class Product {
    private String idProduct;
    private String name;
    private int numberProduct;
    private BigDecimal unitPrice;

    public Product(String idProduct, String name, int numberProduct, BigDecimal unitPrice) {
        this.idProduct = idProduct;
        this.name = name;
        this.numberProduct = numberProduct;
        this.unitPrice = unitPrice;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(int numberProduct) {
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
                "idProduct='" + idProduct + '\'' +
                ", name='" + name + '\'' +
                ", numberProduct=" + numberProduct +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
