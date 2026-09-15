package es.upm.miw.apaw.domain.models.legalprocedure;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class LegalTask {
    @EqualsAndHashCode.Include
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotBlank
    private String title;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Builder.Default
    private LocalDateTime creatingDate = LocalDateTime.now();

    private String notes;

    @NotNull
    @Builder.Default
    private TaskStatus taskStatus = TaskStatus.PENDING;
}
