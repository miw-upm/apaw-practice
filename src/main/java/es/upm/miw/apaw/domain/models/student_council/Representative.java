package es.upm.miw.apaw.domain.models.student_council;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Representative {

    @NotNull
    private LocalDateTime assignmentDate;

    @NotNull
    private String representativeRole;

    @NotNull
    private UserDto user;

    private List<StudentIssue> issues;
}