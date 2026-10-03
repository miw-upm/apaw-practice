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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplianceAssessmentService {

    private final ComplianceAssessmentGateway complianceAssessmentGateway;
    private final EURegulationGateway euRegulationGateway;
    private final UserFinder userFinder;

    public ComplianceAssessment create(@Valid
            ComplianceAssessment complianceAssessment, UUID userId, List<UUID> euRegulationIds) {
        this.assertUniqueEURegulationIds(euRegulationIds);
        UserSnapshot userSnapshot = this.userFinder.read(userId);
        complianceAssessment.setUserSnapshot(userSnapshot);
        complianceAssessment.setEuRegulations(this.readEURegulations(euRegulationIds));
        complianceAssessment.doDefault();
        if (this.complianceAssessmentGateway.existsById(complianceAssessment.getId())) {
            throw new ConflictException("Compliance assessment ID already exists: " + complianceAssessment.getId());
        }
        return this.complianceAssessmentGateway.create(complianceAssessment);
    }

    public ComplianceAssessment read(UUID id) {
        return this.complianceAssessmentGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Compliance assessment id not found: " + id));
    }

    public ComplianceAssessment update(
            UUID id, ComplianceAssessment update, UUID userId, List<UUID> euRegulationIds) {
        ComplianceAssessment storedAssessment = this.read(id);
        this.assertUniqueEURegulationIds(euRegulationIds);

        update.setId(storedAssessment.getId());
        update.setAssessmentDate(storedAssessment.getAssessmentDate());
        if (update.getAiGenerated() == null) {
            update.setAiGenerated(false);
        }
        update.setUserSnapshot(this.userFinder.read(userId));
        update.setEuRegulations(this.readEURegulations(euRegulationIds));
        return this.complianceAssessmentGateway.update(update);
    }

    private void assertUniqueEURegulationIds(List<UUID> euRegulationIds) {
        Set<UUID> uniqueIds = new HashSet<>(euRegulationIds);
        if (uniqueIds.size() != euRegulationIds.size()) {
            throw new ConflictException("Compliance assessment contains repeated EU regulation IDs");
        }
    }

    private List<EURegulation> readEURegulations(List<UUID> euRegulationIds) {
        return euRegulationIds.stream()
                .map(id -> this.euRegulationGateway.read(id)
                        .orElseThrow(() -> new NotFoundException("EU regulation id not found: " + id)))
                .toList();
    }
}
