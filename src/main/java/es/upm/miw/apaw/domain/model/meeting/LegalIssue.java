package es.upm.miw.apaw.domain.model.meeting;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalIssue {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Integer priority;

    private Boolean resolved;

    private LocalDateTime creationDate;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();
        if (this.resolved == null) {
            this.resolved = false;
        }
    }
}
