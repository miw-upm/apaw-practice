package es.upm.miw.apaw.domain.models.university;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectAssignment {
    @NotNull
    private UUID id;
    private Integer capacity;
    private Subject subject;
    @NotNull
    @NotEmpty
    private List<Teacher> teachers;
    @NotNull
    @NotEmpty
    private List<Lesson> lessons;
}
