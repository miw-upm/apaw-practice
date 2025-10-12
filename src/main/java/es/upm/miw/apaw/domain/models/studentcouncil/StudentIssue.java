package es.upm.miw.apaw.domain.models.studentcouncil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StudentIssue {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    @NotBlank
    private String statement;

    private LocalDateTime reportDate;

    @NotNull
    @Builder.Default
    private Boolean closed = false;

    @NotNull
    private Integer urgency;

    private List<IssueReply> replies;
}