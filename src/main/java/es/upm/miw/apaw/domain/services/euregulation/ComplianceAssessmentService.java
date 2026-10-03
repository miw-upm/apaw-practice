package es.upm.miw.apaw.domain.services.euregulation;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.ports.out.euregulation.ComplianceAssessmentGateway;
import es.upm.miw.apaw.domain.ports.out.euregulation.EURegulationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplianceAssessmentService {

    private final ComplianceAssessmentGateway complianceAssessmentGateway;
    private final EURegulationGateway euRegulationGateway;
    private final UserFinder userFinder;

    public ComplianceAssessment create(@Valid
            ComplianceAssessment complianceAssessment, UUID userId, List<UUID> euRegulationIds) {
        UserSnapshot userSnapshot = this.userFinder.read(userId);
        complianceAssessment.setUserSnapshot(userSnapshot);
        complianceAssessment.setEuRegulations(this.readEURegulations(euRegulationIds));
        complianceAssessment.doDefault();
        if (this.complianceAssessmentGateway.existsById(complianceAssessment.getId())) {
            throw new ConflictException("Compliance assessment ID already exists: " + complianceAssessment.getId());
        }
        return this.complianceAssessmentGateway.create(complianceAssessment);
    }

    private List<EURegulation> readEURegulations(List<UUID> euRegulationIds) {
        return euRegulationIds.stream()
                .map(id -> this.euRegulationGateway.read(id)
                        .orElseThrow(() -> new NotFoundException("EU regulation id not found: " + id)))
                .toList();
    }
}
