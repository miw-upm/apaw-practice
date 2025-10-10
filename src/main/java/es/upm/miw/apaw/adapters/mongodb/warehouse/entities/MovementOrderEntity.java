package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document

public class MovementOrderEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private LocalDateTime registrationDate;
    private String typeOrder;
    private String partnerName;
    private String partnerAddress;
    private Boolean completedOrder;

    @DBRef
    private List<OrderDetailEntity> orderDetailEntities;

    private UUID userId;


    public MovementOrderEntity(MovementOrder movementOrder) {
        BeanUtils.copyProperties(movementOrder, this, "user", "orderDetails");

        this.id = movementOrder.getId() != null ? movementOrder.getId() : UUID.randomUUID();
        this.userId = movementOrder.getUser() != null ? movementOrder.getUser().getId() : null;

        if (movementOrder.getOrderDetails() != null) {
            this.orderDetailEntities = movementOrder.getOrderDetails().stream()
                    .map(OrderDetailEntity::new)
                    .toList();
        }
    }

    public MovementOrder toMovementOrder() {
        MovementOrder movementOrder = new MovementOrder();
        BeanUtils.copyProperties(this, movementOrder, "orderDetailEntities", "userId");

        movementOrder.setId(this.id); // ✅ ahora sí se mapea
        movementOrder.setUser(UserDto.builder().id(this.userId).build());

        if (this.orderDetailEntities != null) {
            movementOrder.setOrderDetails(
                    this.orderDetailEntities.stream()
                            .filter(Objects::nonNull) // ✅ evita NPE si algún OrderDetailEntity es null
                            .map(OrderDetailEntity::toOrderDetail)
                            .toList()
            );
        }

        return movementOrder;
    }

    public void fromMovementOrder(MovementOrder movementOrder) {
        BeanUtils.copyProperties(movementOrder, this, "user", "orderDetails");

        if (movementOrder.getOrderDetails() != null) {
            this.orderDetailEntities = movementOrder.getOrderDetails().stream()
                    .map(OrderDetailEntity::new)
                    .toList();
        }
        this.userId = movementOrder.getUser() != null ? movementOrder.getUser().getId() : null;
    }

}
