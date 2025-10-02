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
    private UUID issueId;

    @NotNull
    private String issueDescription;

    @NotNull
    private LocalDateTime issueDate;

    @NotNull
    private Boolean issueResolved;

    @NotNull
    private Integer issuePriority;

    private List<IssueReply> replies;
}