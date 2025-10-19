package es.upm.miw.apaw.adapters.mongodb.warehouse.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
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

    @NotNull
    private LocalDateTime registrationDate;

    @NotBlank
    private String typeOrder;

    private String partnerName;
    private String partnerAddress;
    private Boolean completedOrder;

    private List<OrderDetailEntity> orderDetailEntities;

    private UUID userId;


    public MovementOrder toMovementOrder() {
        return MovementOrder.builder()
                .id(this.id)
                .registrationDate(this.registrationDate)
                .typeOrder(this.typeOrder)
                .partnerName(this.partnerName)
                .partnerAddress(this.partnerAddress)
                .completedOrder(this.completedOrder)
                .orderDetails(this.orderDetailEntities == null ? null :
                        this.orderDetailEntities.stream()
                                .map(OrderDetailEntity::toOrderDetail)
                                .toList())
                .user(UserDto.builder().id(this.userId).build())
                .build();
    }

    public static MovementOrderEntity fromMovementOrder(MovementOrder movementOrder) {
        return MovementOrderEntity.builder()
                .id(movementOrder.getId() != null ? movementOrder.getId() : UUID.randomUUID())
                .registrationDate(movementOrder.getRegistrationDate())
                .typeOrder(movementOrder.getTypeOrder())
                .partnerName(movementOrder.getPartnerName())
                .partnerAddress(movementOrder.getPartnerAddress())
                .completedOrder(movementOrder.getCompletedOrder())
                .orderDetailEntities(movementOrder.getOrderDetails() == null ? null :
                        movementOrder.getOrderDetails().stream()
                                .map(OrderDetailEntity::fromOrderDetail)
                                .toList())
                .userId(movementOrder.getUser() == null ? null : movementOrder.getUser().getId())
                .build();
    }

}
