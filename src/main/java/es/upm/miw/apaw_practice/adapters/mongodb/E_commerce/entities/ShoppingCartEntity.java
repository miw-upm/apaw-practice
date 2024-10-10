package es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class ShoppingCartEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private Integer shoppingNum;
    private LocalDateTime shoppingTime;
    private Boolean isPaid;
    private BigDecimal totalPrice;

    @DBRef
    private List<ProductEntity> products;

    public ShoppingCartEntity() {
        //Empty for framework
    }

    public ShoppingCartEntity(Integer shoppingNum, LocalDateTime shoppingTime, Boolean isPaid, BigDecimal totalPrice, List<ProductEntity> products) {
        this.id = UUID.randomUUID().toString();
        this.shoppingNum = shoppingNum;
        this.shoppingTime = shoppingTime;
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartEntity that = (ShoppingCartEntity) o;
        return Objects.equals(shoppingNum, that.shoppingNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingNum);
    }

    // ToString method
    @Override
    public String toString() {
        return "ShoppingCartEntity{" +
                "id='" + id + '\'' +
                ", shoppingNum=" + shoppingNum +
                ", shoppingTime=" + shoppingTime +
                ", isPaid=" + isPaid +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}
