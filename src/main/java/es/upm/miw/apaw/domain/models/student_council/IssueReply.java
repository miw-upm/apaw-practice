package es.upm.miw.apaw.domain.models.student_council;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueReply {

    @NotNull
    private String reason;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private BigDecimal compensation;
}