package es.upm.miw.apaw.domain.models.studentcouncil;

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


    private UUID id;

    @NotNull
    private String statement;


    private LocalDateTime reportDate;


    private Boolean closed;

    @NotNull
    private Integer urgency;

    private List<IssueReply> replies;
}