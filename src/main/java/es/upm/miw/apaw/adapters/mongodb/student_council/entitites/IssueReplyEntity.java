package es.upm.miw.apaw.adapters.mongodb.student_council.entitites;

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
}
