package es.upm.miw.apaw.domain.models.warehouse;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovementOrder {

    @NotNull
    private UUID idMovementOrder;

    //@NotNull USER ID
    private UUID id;

    @NotNull
    private LocalDateTime registrationDate;

    private String typeOrder;
    private String partnerName;
    private String partnerAddress;

    @NotNull
    private Boolean isCompleted;

    //private List<OrderDetail> orderDetails;
}
