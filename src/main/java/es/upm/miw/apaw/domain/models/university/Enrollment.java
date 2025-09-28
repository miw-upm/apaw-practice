package es.upm.miw.apaw.domain.models.university;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @NotNull
    @NotBlank
    private String code;
    @NotNull
    @NotBlank
    private String semester;
    @NotNull
    private LocalDateTime enrollmentDate;
    @NotNull
    @NotEmpty
    private List<SubjectAssignment> subjectAssignments;
    @NotNull
    private UserDto student;
}
