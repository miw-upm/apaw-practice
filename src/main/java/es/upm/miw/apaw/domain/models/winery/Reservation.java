package es.upm.miw.apaw.domain.models.winery;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private UUID id;
    private LocalDateTime bookingDate;
    private BigDecimal totalCost;
    private Boolean confirmed;
    @NotNull
    private UserDto user;
    @NotNull
    private TastingSession tastingSession;
}
