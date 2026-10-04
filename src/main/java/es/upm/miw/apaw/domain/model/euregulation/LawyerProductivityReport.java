package es.upm.miw.apaw.domain.model.euregulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
}
