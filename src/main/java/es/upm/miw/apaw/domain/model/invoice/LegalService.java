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
    private UUID id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal fee;

    @NotNull
    private Boolean requiresAppointment;

    @NotNull
    private ServiceCategory category;

    @NotNull
    private LegalArea legalArea;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.requiresAppointment == null) {
            this.requiresAppointment = false;
        }
        if (this.category == null) {
            this.category = ServiceCategory.CONSULTING;
        }
        if (this.legalArea == null) {
            this.legalArea = LegalArea.CIVIL;
        }
    }
}