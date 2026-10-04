package es.upm.miw.apaw.domain.model.euregulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawyerProductivityReport {
    private String responsibleLawyer;
    private long totalAssessments;
    private long aiGeneratedCount;
    private long manualCount;
    private BigDecimal aiRatio;

    public LawyerProductivityReport(
            String responsibleLawyer,
            Long totalAssessments,
            Long aiGeneratedCount,
            Long manualCount) {
        this.responsibleLawyer = responsibleLawyer;
        this.totalAssessments = totalAssessments;
        this.aiGeneratedCount = aiGeneratedCount;
        this.manualCount = manualCount;
        this.aiRatio = BigDecimal.valueOf(aiGeneratedCount)
                .divide(BigDecimal.valueOf(totalAssessments), 10, RoundingMode.HALF_UP)
                .stripTrailingZeros();
    }
}
