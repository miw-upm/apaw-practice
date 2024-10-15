package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class DeliveryOrderItemEntity {

    @Id
    private String id;
    private BigDecimal price;
    private Integer quantity;
    @DBRef
    private MenuEntity menu;

    public DeliveryOrderItemEntity() {
    }

    public DeliveryOrderItemEntity(BigDecimal price, Integer quantity, MenuEntity menu) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
        this.quantity = quantity;
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "DeliveryOrderItem{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", menu=" + menu +
                '}';
    }

    public DeliveryOrderItem toDeliveryOrderItem() {
        return new DeliveryOrderItem(id, price, quantity, menu.toMenu());
    }
}
