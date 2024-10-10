package es.upm.miw.apaw_practice.domain.models.E_commerce_model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart {
    private Integer shoppingNum;
    private LocalDateTime shoppingTime;
    private Boolean isPaid;
    private BigDecimal totalPrice;
    private List<Product> products;

    public ShoppingCart() {
        // Empty for framework
    }

    public ShoppingCart(Integer shoppingNum, LocalDateTime shoppingTime, Boolean isPaid, BigDecimal totalPrice, List<Product> products) {
        this.shoppingNum = shoppingNum;
        this.shoppingTime = shoppingTime;
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public Integer getShoppingNum() {
        return shoppingNum;
    }

    public void setShoppingNum(Integer shoppingNum) {
        this.shoppingNum = shoppingNum;
    }

    public LocalDateTime getShoppingTime() {
        return shoppingTime;
    }

    public void setShoppingTime(LocalDateTime shoppingTime) {
        this.shoppingTime = shoppingTime;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                ", shoppingNum=" + shoppingNum +
                ", shoppingTime=" + shoppingTime +
                ", isPaid=" + isPaid +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}
