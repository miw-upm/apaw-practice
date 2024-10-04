package es.upm.miw.apaw_practice.domain.models.delivery_food;

import java.math.BigDecimal;

public class DeliveryOrderItem {

    private String id;
    private BigDecimal price;
    private Integer quantity;
    private Menu menu;

    public DeliveryOrderItem() {
    }

    public DeliveryOrderItem(String id, BigDecimal price, Integer quantity, Menu menu) {
        this.id = id;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
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
}
