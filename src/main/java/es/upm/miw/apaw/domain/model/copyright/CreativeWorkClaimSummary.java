package es.upm.miw.apaw.domain.model.copyright;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreativeWorkClaimSummary {
    private String registrationCode;
    private Long claimCount;
    private BigDecimal totalRequestedCompensation;
    private UUID authorId;
    private UserSnapshot author;

    public CreativeWorkClaimSummary(String registrationCode, UUID authorId, Long claimCount, BigDecimal totalRequestedCompensation) {
        this.registrationCode = registrationCode;
        this.authorId = authorId;
        this.claimCount = claimCount;
        this.totalRequestedCompensation = totalRequestedCompensation;
    }
}
