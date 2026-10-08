package es.upm.miw.apaw.domain.model.taskmanagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationTask {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDate dueDate;

    private Integer priority;

    private Boolean completion;

    private BigDecimal estimatedHours;

    @NotNull
    private List<@NotNull UUID> taskCommentIds;

    @NotNull
    private UUID ownerId;
}
