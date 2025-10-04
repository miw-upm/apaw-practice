package es.upm.miw.apaw.domain.models.student_council;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentIssue {

    @NotNull
    private UUID id;

    @NotNull
    private String statement;

    @NotNull
    private LocalDateTime reportDate;

    @NotNull
    private Boolean closed;

    @NotNull
    private Integer urgency;

    private List<IssueReply> replies;
}