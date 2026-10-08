package es.upm.miw.apaw.domain.services.euregulation;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessmentFindCriteria;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessmentPatch;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceByAreaReport;
import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.model.euregulation.LawyerProductivityReport;
import es.upm.miw.apaw.domain.ports.out.euregulation.ComplianceAssessmentGateway;
import es.upm.miw.apaw.domain.ports.out.euregulation.EURegulationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public List<ComplianceAssessment> findAll() {
        return this.complianceAssessmentGateway.findAll();
    }

    public List<ComplianceAssessment> find(ComplianceAssessmentFindCriteria criteria) {
        ComplianceAssessmentFindCriteria safeCriteria = criteria == null
                ? new ComplianceAssessmentFindCriteria()
                : criteria;
        List<ComplianceAssessment> assessments = this.complianceAssessmentGateway.find(safeCriteria);
        if (assessments.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = assessments.stream()
                .map(ComplianceAssessment::getUserSnapshot)
                .filter(user -> user != null && user.getId() != null)
                .map(UserSnapshot::getId)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        Map<UUID, UserSnapshot> usersById = userIds.isEmpty()
                ? Map.of()
                : this.findUsersById(userIds);
        return assessments.stream()
                .filter(assessment -> this.matchesDaysToNearestDeadline(safeCriteria, assessment))
                .filter(assessment -> this.matchesUserFirstName(
                        safeCriteria, usersById.get(this.getUserId(assessment))))
                .map(assessment -> this.enrichUserSnapshot(assessment, usersById))
                .toList();
    }

    private boolean matchesDaysToNearestDeadline(
            ComplianceAssessmentFindCriteria criteria, ComplianceAssessment assessment) {
        if (!criteria.hasDaysToNearestDeadline()) {
            return true;
        }
        return assessment.getComplianceDeadline() != null
                && ChronoUnit.DAYS.between(LocalDate.now(), assessment.getComplianceDeadline())
                == criteria.getDaysToNearestDeadline();
    }

    private boolean matchesUserFirstName(ComplianceAssessmentFindCriteria criteria, UserSnapshot user) {
        return !criteria.hasUserFirstName()
                || (user != null && user.getFirstName() != null
                && user.getFirstName().toLowerCase(Locale.ROOT)
                .contains(criteria.getUserFirstName().toLowerCase(Locale.ROOT)));
    }

    private ComplianceAssessment enrichUserSnapshot(
            ComplianceAssessment assessment, Map<UUID, UserSnapshot> usersById) {
        UserSnapshot user = usersById.get(this.getUserId(assessment));
        if (user != null) {
            assessment.setUserSnapshot(user);
        }
        return assessment;
    }

    private UUID getUserId(ComplianceAssessment assessment) {
        return assessment.getUserSnapshot() == null ? null : assessment.getUserSnapshot().getId();
    }

    private Map<UUID, UserSnapshot> findUsersById(Set<UUID> userIds) {
        List<UserSnapshot> users = this.userFinder.findByIds(userIds);
        if (users == null) {
            return Map.of();
        }
        return users.stream()
                .filter(user -> user != null && user.getId() != null)
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity(), (first, ignored) -> first));
    }

    public List<ComplianceByAreaReport> findComplianceByAreaReport() {
        return this.complianceAssessmentGateway.findComplianceByAreaReport();
    }

    public List<LawyerProductivityReport> findLawyerProductivityReport() {
        return this.complianceAssessmentGateway.findLawyerProductivityReport();
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

    public ComplianceAssessment patch(
            UUID id, ComplianceAssessmentPatch patch, UUID userId, List<UUID> euRegulationIds) {
        ComplianceAssessment storedAssessment = this.read(id);

        if (patch.responsibleLawyer() != null) {
            storedAssessment.setResponsibleLawyer(patch.responsibleLawyer());
        }
        if (patch.complianceDeadline() != null) {
            storedAssessment.setComplianceDeadline(patch.complianceDeadline());
        }
        if (patch.nextReviewDate() != null) {
            storedAssessment.setNextReviewDate(patch.nextReviewDate());
        }
        if (patch.correctiveActions() != null) {
            storedAssessment.setCorrectiveActions(patch.correctiveActions());
        }
        if (patch.supportingDocumentation() != null) {
            storedAssessment.setSupportingDocumentation(patch.supportingDocumentation());
        }
        if (patch.notes() != null) {
            storedAssessment.setNotes(patch.notes());
        }
        if (patch.aiGenerated() != null) {
            storedAssessment.setAiGenerated(patch.aiGenerated());
        }
        if (patch.complianceLevel() != null) {
            storedAssessment.setComplianceLevel(patch.complianceLevel());
        }
        if (patch.riskLevel() != null) {
            storedAssessment.setRiskLevel(patch.riskLevel());
        }
        if (userId != null) {
            storedAssessment.setUserSnapshot(this.userFinder.read(userId));
        }
        if (euRegulationIds != null) {
            this.assertUniqueEURegulationIds(euRegulationIds);
            storedAssessment.setEuRegulations(this.readEURegulations(euRegulationIds));
        }

        return this.complianceAssessmentGateway.update(storedAssessment);
    }

    public void delete(UUID id) {
        this.read(id);
        if (this.complianceAssessmentGateway.isReferenced(id)) {
            throw new ConflictException("Compliance assessment is referenced by an EU regulation: " + id);
        }
        this.complianceAssessmentGateway.delete(id);
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
