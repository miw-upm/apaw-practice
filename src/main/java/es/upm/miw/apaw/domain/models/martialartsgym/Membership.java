package es.upm.miw.apaw.domain.models.martialartsgym;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Membership {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private BigDecimal monthlyFee;

    @NotNull
    private LocalDate activationDate;

    @NotNull
    private Boolean isCurrentlyActive;

    @NotNull
    private UserDto user;
}
