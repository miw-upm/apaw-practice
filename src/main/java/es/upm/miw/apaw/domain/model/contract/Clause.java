package es.upm.miw.apaw.domain.model.contract;

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
public class Clause {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    private ClauseType type;

    @NotBlank
    private String content;

    @NotNull
    private LocalDate effectiveFrom;

    private LocalDate effectiveUntil;

    private String notes;

    @NotNull
    private Integer version;

    public void doDefault() {
        this.id = UUID.randomUUID();

        if (this.version == null) {
            this.version = 1;
        }

        if (this.type == null) {
            this.type = ClauseType.OTHER;
        }
    }
}