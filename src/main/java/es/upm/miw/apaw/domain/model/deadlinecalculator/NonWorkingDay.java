package es.upm.miw.apaw.domain.model.deadlinecalculator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NonWorkingDay {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String description;

    @NotNull
    private ScopeLevel scopeLevel;

    private String region;

    private String city;

    private Boolean recurring;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.recurring == null) {
            this.recurring = false;
        }
    }
}
