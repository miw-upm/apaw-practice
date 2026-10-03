package es.upm.miw.apaw.domain.model.expertdirectoryservices.reports;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalExpertProfileSpecialtyReport {
    private String specialtyArea;
    private long totalProfiles;
    private long totalSchedules;
    private double averageRateAmount;
    private double averageYearsOfExperience;
    private UserSnapshot mostVeteranExpert;
}
