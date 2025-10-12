package es.upm.miw.apaw.domain.models.studentcouncil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueReply {

    @NotNull
    @NotBlank
    private String reason;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private BigDecimal compensation;
}