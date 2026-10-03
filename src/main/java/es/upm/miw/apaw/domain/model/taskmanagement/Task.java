package es.upm.miw.apaw.domain.model.taskmanagement;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    private String description;

    private LocalDate dueDate;

    @NotNull
    private Integer priority;

    @NotNull
    private Boolean completion;

    private BigDecimal estimatedHours;

    private List<TaskComment> comments;

    @NotNull
    private UserSnapshot owner;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.priority = 3;
        this.completion = false;
        this.comments = new ArrayList<>();
    }
}
