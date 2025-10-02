package es.upm.miw.apaw.domain.models.clothingstore;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @NotNull
    private UUID orderId;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private BigDecimal total;

    private Integer itemCount;
}
