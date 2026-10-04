package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import es.upm.miw.apaw.domain.model.euregulation.ApplicationArea;

import java.math.BigDecimal;

public interface ComplianceByAreaReportProjection {
    ApplicationArea getApplicationArea();

    Long getTotalAssessments();

    Long getCompliantCount();

    Long getPartiallyCompliantCount();

    Long getNonCompliantCount();

    Long getPendingReviewCount();

    BigDecimal getComplianceRate();
}
