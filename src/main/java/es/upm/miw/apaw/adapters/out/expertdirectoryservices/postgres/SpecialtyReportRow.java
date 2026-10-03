package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.reports.LegalExpertProfileSpecialtyReport;

public record SpecialtyReportRow(String specialtyArea, long totalProfiles, long totalSchedules,
                                 Double averageRateAmount, Double averageYearsOfExperience) {

    public LegalExpertProfileSpecialtyReport toDomain(UserSnapshot mostVeteranExpert) {
        return LegalExpertProfileSpecialtyReport.builder()
                .specialtyArea(this.specialtyArea)
                .totalProfiles(this.totalProfiles)
                .totalSchedules(this.totalSchedules)
                .averageRateAmount(round(this.averageRateAmount))
                .averageYearsOfExperience(round(this.averageYearsOfExperience))
                .mostVeteranExpert(mostVeteranExpert)
                .build();
    }

    private static double round(Double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
