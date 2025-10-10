package es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites;


import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class StudentIssueEntity {

    @Id
    private UUID id;

    private String statement;
    private LocalDateTime reportDate;
    private Boolean closed;
    private Integer urgency;

    private List<IssueReplyEntity> replies;

    public StudentIssue toStudentIssue() {
        return StudentIssue.builder()
                .id(this.id)
                .statement(this.statement)
                .reportDate(this.reportDate)
                .closed(this.closed)
                .urgency(this.urgency)
                .replies(this.replies == null ? new ArrayList<>() :
                        this.replies.stream().map(IssueReplyEntity::toIssueReply).toList())
                .build();
    }

}
