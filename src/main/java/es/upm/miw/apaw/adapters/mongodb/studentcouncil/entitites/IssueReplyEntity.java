package es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites;

import es.upm.miw.apaw.domain.models.studentcouncil.IssueReply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class IssueReplyEntity {
    @Id
    private UUID id;

    private String reason;
    private LocalDateTime createDate;
    private BigDecimal compensation;

    public IssueReply toIssueReply() {
        return IssueReply.builder()
                .reason(this.reason)
                .createDate(this.createDate)
                .compensation(this.compensation)
                .build();
    }

}
