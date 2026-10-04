package es.upm.miw.apaw.adapters.in.reports;

import es.upm.miw.apaw.domain.model.euregulation.LawyerProductivityReport;

import java.math.BigDecimal;

public record LawyerProductivityReportDto(
        String responsibleLawyer,
        long totalAssessments,
        long aiGeneratedCount,
        long manualCount,
        BigDecimal aiRatio
) {
    public static LawyerProductivityReportDto fromDomain(LawyerProductivityReport report) {
        return new LawyerProductivityReportDto(
                report.getResponsibleLawyer(),
                report.getTotalAssessments(),
                report.getAiGeneratedCount(),
                report.getManualCount(),
                report.getAiRatio());
    }
}
