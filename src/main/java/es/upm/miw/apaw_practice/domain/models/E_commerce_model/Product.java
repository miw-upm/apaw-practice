package es.upm.miw.apaw_practice.domain.models.E_commerce_model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private Boolean isAvailable;

    // Constructor
    public Product(Long id, String name, BigDecimal price, LocalDateTime createdAt, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
