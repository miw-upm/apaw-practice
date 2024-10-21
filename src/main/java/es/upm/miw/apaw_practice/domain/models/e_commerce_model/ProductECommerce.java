package es.upm.miw.apaw_practice.domain.models.e_commerce_model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductECommerce {

    private String productName;
    private Integer numberProduct;
    private BigDecimal unitPrice;

    public ProductECommerce() {
        // Empty for framework
    }

    public static ProductECommerceBuilders.ProductName builder() {
        return new Builder();
    }

    public ProductECommerce(String productName, Integer numberProduct, BigDecimal unitPrice) {
        this.productName = productName;
        this.numberProduct = numberProduct;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getNumberProduct() {
        return numberProduct;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumberProduct(Integer numberProduct) {
        this.numberProduct = numberProduct;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ProductECommerce{" +
                "productName='" + productName + '\'' +
                ", numberProduct=" + numberProduct +
                ", unitPrice=" + unitPrice +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ProductECommerce product = (ProductECommerce) object;
        return Objects.equals(productName, product.productName) &&
                Objects.equals(numberProduct, product.numberProduct) &&
                Objects.equals(unitPrice, product.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, numberProduct, unitPrice);
    }

    // Builder 类实现分步构建
    public static class Builder implements ProductECommerceBuilders.ProductName, ProductECommerceBuilders.NumberProduct,
            ProductECommerceBuilders.UnitPrice, ProductECommerceBuilders.Builder {

        private final ProductECommerce product;

        public Builder() {
            this.product = new ProductECommerce();
        }

        @Override
        public ProductECommerceBuilders.NumberProduct productName(String productName) {
            this.product.productName = productName;
            return this;
        }

        @Override
        public ProductECommerceBuilders.UnitPrice numberProduct(Integer numberProduct) {
            this.product.numberProduct = numberProduct;
            return this;
        }

        @Override
        public ProductECommerceBuilders.Builder unitPrice(BigDecimal unitPrice) {
            this.product.unitPrice = unitPrice;
            return this;
        }

        @Override
        public ProductECommerce build() {
            return this.product;
        }
    }
}
