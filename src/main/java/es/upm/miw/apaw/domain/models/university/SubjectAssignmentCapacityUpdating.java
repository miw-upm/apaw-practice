package es.upm.miw.apaw.domain.models.university;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectAssignmentCapacityUpdating {
    @NotNull
    private UUID id;
    @NotNull
    private Integer capacity;
}