package es.upm.miw.apaw.domain.models.warehouse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    private UUID id;

    @NotNull
    private LocalDateTime registrationDate;

    @NotBlank
    private String typeOrder;

    private String partnerName;
    private String partnerAddress;

    @NotBlank
    @NotEmpty
    private List<OrderDetail> orderDetails;

    @NotNull
    private Boolean completedOrder;

}
