package es.upm.miw.apaw.domain.model.immigrationissues;

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
public class LawBasis {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String lawCode;

    @NotBlank
    private String lawName;

    @NotNull
    private Integer articleNumber;

    @NotNull
    private LocalDate publishedOn;

    private Boolean active;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.active == null) {
            this.active = true;
        }
    }
}