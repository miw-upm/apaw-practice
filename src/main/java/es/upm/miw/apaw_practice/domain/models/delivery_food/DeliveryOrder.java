package es.upm.miw.apaw_practice.domain.models.delivery_food;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class DeliveryOrder {

    private String id;
    private String deliveryAddress;
    private BigDecimal totalPrice;
    private String customerName;
    private LocalDateTime orderDate;
    private Boolean delivered;
    private List<DeliveryOrderItem> deliveryOrderItems;

    public DeliveryOrder() {
    }

    public DeliveryOrder(String id, String deliveryAddress, BigDecimal totalPrice, String customerName, LocalDateTime orderDate, Boolean delivered, List<DeliveryOrderItem> deliveryOrderItems) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.delivered = delivered;
        this.deliveryOrderItems = deliveryOrderItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<DeliveryOrderItem> getDeliveryOrderItems() {
        return deliveryOrderItems;
    }

    public void setDeliveryOrderItems(List<DeliveryOrderItem> deliveryOrderItems) {
        this.deliveryOrderItems = deliveryOrderItems;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryOrder that = (DeliveryOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "id='" + id + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", delivered=" + delivered +
                ", deliveryOrderItems=" + deliveryOrderItems +
                '}';
    }
}
