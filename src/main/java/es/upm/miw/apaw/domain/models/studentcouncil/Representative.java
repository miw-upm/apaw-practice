package es.upm.miw.apaw.domain.models.studentcouncil;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Representative {

    @NotNull
    private LocalDateTime joinDate;

    @NotNull
    private String responsibility;

    @NotNull
    private UserDto representative;

    private List<StudentIssue> topics;
}