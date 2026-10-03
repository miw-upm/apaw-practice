package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.reports.LegalExpertProfileSpecialtyReport;

import java.util.UUID;

public record SpecialtyReportRow(String specialtyArea, long totalProfiles, long totalSchedules,
                                 Double averageRateAmount, Double averageYearsOfExperience,
                                 UUID mostVeteranUserId) {

    public LegalExpertProfileSpecialtyReport toDomain() {
        return LegalExpertProfileSpecialtyReport.builder()
                .specialtyArea(this.specialtyArea)
                .totalProfiles(this.totalProfiles)
                .totalSchedules(this.totalSchedules)
                .averageRateAmount(round(this.averageRateAmount))
                .averageYearsOfExperience(round(this.averageYearsOfExperience))
                .mostVeteranExpert(UserSnapshot.builder().id(this.mostVeteranUserId).build())
                .build();
    }

    private static double round(Double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
