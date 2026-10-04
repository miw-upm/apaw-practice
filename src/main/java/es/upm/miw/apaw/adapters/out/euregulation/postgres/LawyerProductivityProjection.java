package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import java.math.BigDecimal;

public interface LawyerProductivityProjection {
    String getResponsibleLawyer();

    Long getTotalAssessments();

    Long getAiGeneratedCount();

    Long getManualCount();

    BigDecimal getAiRatio();
}
