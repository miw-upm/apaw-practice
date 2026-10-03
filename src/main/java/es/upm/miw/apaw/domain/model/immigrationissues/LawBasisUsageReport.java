package es.upm.miw.apaw.domain.model.immigrationissues;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawBasisUsageReport {
    private String clientImmigrationStatus;
    private String lawCode;
    private long totalIssues;
}