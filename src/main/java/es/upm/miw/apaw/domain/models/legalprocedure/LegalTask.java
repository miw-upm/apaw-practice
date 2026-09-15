package es.upm.miw.apaw.domain.models.legalprocedure;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LegalTask {
    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    private LocalDateTime creatingDate;

    private String notes;

    private TaskStatus taskStatus;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.creatingDate = LocalDateTime.now();
        if (this.taskStatus == null) {
            this.taskStatus = TaskStatus.PENDING;
        }
    }
}
