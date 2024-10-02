package es.upm.miw.apaw_practice.domain.models.delivery_food;

import java.math.BigDecimal;

public class DeliveryOrderItem {

    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Menu menu;

    public DeliveryOrderItem() {
    }

    public DeliveryOrderItem(BigDecimal price, Integer quantity, BigDecimal totalPrice, Menu menu) {
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.menu = menu;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
                "price=" + price +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", menu=" + menu +
                '}';
    }
}
