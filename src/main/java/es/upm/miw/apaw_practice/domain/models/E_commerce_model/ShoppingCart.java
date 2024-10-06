package es.upm.miw.apaw_practice.domain.models.E_commerce_model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart {
    private LocalDateTime shoppingTime;
    private boolean isPaid;
    private BigDecimal totalPrice;
    private List<Product> products;

    public ShoppingCart(LocalDateTime shoppingTime, boolean isPaid, BigDecimal totalPrice, List<Product> products) {
        this.shoppingTime = shoppingTime;
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public LocalDateTime getShoppingTime() {
        return shoppingTime;
    }

    public void setShoppingTime(LocalDateTime shoppingTime) {
        this.shoppingTime = shoppingTime;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
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
                "shoppingTime=" + shoppingTime +
                ", isPaid=" + isPaid +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}
