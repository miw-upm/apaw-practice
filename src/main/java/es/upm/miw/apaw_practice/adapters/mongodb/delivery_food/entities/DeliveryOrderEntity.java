package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class DeliveryOrderEntity {

    @Id
    private String id;
    private String deliveryAddress;
    private String customerName;
    private LocalDateTime orderDate;
    private Boolean delivered;

    @DBRef
    private List<DeliveryOrderItemEntity> deliveryOrderItems;

    public DeliveryOrderEntity() {
    }

    public DeliveryOrderEntity(String deliveryAddress, String customerName, LocalDateTime orderDate, Boolean delivered, List<DeliveryOrderItemEntity> deliveryOrderItems) {
        this.id = UUID.randomUUID().toString();
        this.deliveryAddress = deliveryAddress;
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

    public List<DeliveryOrderItemEntity> getDeliveryOrderItems() {
        return deliveryOrderItems;
    }

    public void setDeliveryOrderItems(List<DeliveryOrderItemEntity> deliveryOrderItems) {
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
        DeliveryOrderEntity that = (DeliveryOrderEntity) o;
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
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", delivered=" + delivered +
                ", deliveryOrderItems=" + deliveryOrderItems +
                '}';
    }

    public DeliveryOrder toDeliveryOrder() {
        List<DeliveryOrderItem> orderItemList = null;
        if (this.deliveryOrderItems != null) {
            orderItemList = this.deliveryOrderItems.stream()
                    .map(DeliveryOrderItemEntity::toDeliveryOrderItem)
                    .toList();
        }
        return new DeliveryOrder(id, deliveryAddress, customerName, orderDate, delivered, orderItemList);
    }
}
