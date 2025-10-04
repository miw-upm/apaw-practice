package es.upm.miw.apaw.adapters.mongodb.student_council.entitites;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
}
