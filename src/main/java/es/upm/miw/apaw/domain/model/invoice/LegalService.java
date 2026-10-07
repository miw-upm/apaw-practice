package es.upm.miw.apaw.domain.model.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalService {

    @EqualsAndHashCode.Include
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal fee;

    @NotNull
    @Builder.Default
    private Boolean requiresAppointment = false;

    @NotNull
    private ServiceCategory category;

    @NotNull
    private LegalArea legalArea;
}