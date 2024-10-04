package es.upm.miw.apaw_practice.domain.models.E_commerce_model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private Boolean isPaid;
    private List<Product> products; // An order can have multiple products

    // Constructor
    public Order(Long id, LocalDate orderDate, BigDecimal totalAmount, Boolean isPaid, List<Product> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
        this.products = products;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", isPaid=" + isPaid +
                ", products=" + products +
                '}';
    }
}
