package es.upm.miw.apaw.adapters.mongodb.clothingstore.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.models.clothingstore.Order;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    private LocalDate date;
    private BigDecimal total;
    private Integer itemCount;
    private String status;
    private String paymentMethod;

    @DBRef
    private List<GarmentEntity> garments;

    private UUID userId;

    private InvoiceEntity invoice;

    public OrderEntity(Order order) {
        BeanUtils.copyProperties(order, this, "items", "user", "invoice");
        this.userId = (order.getUser() == null) ? null : order.getUser().getId();
        this.invoice = (order.getInvoice() == null) ? null : new InvoiceEntity(order.getInvoice());
        this.garments = (order.getItems() == null) ? null :
                order.getItems().stream().map(GarmentEntity::new).toList();
    }

    public Order toOrder() {
        Order order = new Order();
        BeanUtils.copyProperties(this, order, "garments", "invoice", "userId");
        if (this.invoice != null) order.setInvoice(this.invoice.toInvoice());
        if (this.garments != null)
            order.setItems(this.garments.stream().map(GarmentEntity::toGarment).toList());
        if (this.userId != null)
            order.setUser(UserDto.builder().id(this.userId).build());
        return order;
    }

    public void fromOrder(Order order) {
        BeanUtils.copyProperties(order, this, "items", "user", "invoice");
        this.userId = (order.getUser() == null) ? null : order.getUser().getId();
        this.invoice = (order.getInvoice() == null) ? null : new InvoiceEntity(order.getInvoice());
        this.garments = (order.getItems() == null) ? null :
                order.getItems().stream().map(GarmentEntity::new).toList();
    }
}

