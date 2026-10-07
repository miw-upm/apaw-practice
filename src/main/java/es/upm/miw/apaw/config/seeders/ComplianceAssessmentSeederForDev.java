package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.euregulation.postgres.ComplianceAssessmentEntity;
import es.upm.miw.apaw.adapters.out.euregulation.postgres.ComplianceAssessmentRepository;
import es.upm.miw.apaw.adapters.out.euregulation.postgres.EURegulationEntity;
import es.upm.miw.apaw.adapters.out.euregulation.postgres.EURegulationRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel;
import es.upm.miw.apaw.domain.model.euregulation.RiskLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(7)
@RequiredArgsConstructor
public class ComplianceAssessmentSeederForDev implements ApplicationRunner {

    private static final String ASSESSMENT_ID_PREFIX = "eeeeeeee-1111-2222-3333-44445555";
    public static final UUID USER_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");
    public static final UUID USER_ID_1 = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");
    public static final UUID ID_0 = UUID.fromString(ASSESSMENT_ID_PREFIX + "0000");
    public static final UUID ID_1 = UUID.fromString(ASSESSMENT_ID_PREFIX + "0001");
    public static final UUID ID_2 = UUID.fromString(ASSESSMENT_ID_PREFIX + "0002");
    public static final UUID ID_3 = UUID.fromString(ASSESSMENT_ID_PREFIX + "0003");
    public static final UUID ID_4 = UUID.fromString(ASSESSMENT_ID_PREFIX + "0004");

    private final ComplianceAssessmentRepository complianceAssessmentRepository;
    private final EURegulationRepository euRegulationRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        List<ComplianceAssessmentEntity> assessments = List.of(
                        this.assessment0(), this.assessment1(), this.assessment2(),
                        this.assessment3(), this.assessment4()).stream()
                .filter(assessment -> !this.complianceAssessmentRepository.existsById(assessment.getId()))
                .toList();
        this.complianceAssessmentRepository.saveAll(assessments);
        log.info("Compliance assessments seeded: {}", assessments.size());
    }

    private ComplianceAssessmentEntity assessment0() {
        ComplianceAssessment assessment = ComplianceAssessment.builder()
                .id(ID_0)
                .responsibleLawyer("Laura García")
                .assessmentDate(LocalDate.of(2025, 1, 15))
                .complianceDeadline(LocalDate.of(2025, 6, 30))
                .nextReviewDate(LocalDate.of(2025, 4, 15))
                .correctiveActions("Review data retention and consent procedures.")
                .supportingDocumentation("GDPR internal audit report")
                .notes("Initial data protection assessment")
                .aiGenerated(false)
                .complianceLevel(ComplianceLevel.PARTIALLY_COMPLIANT)
                .riskLevel(RiskLevel.MEDIUM)
                .userSnapshot(UserSnapshot.builder().id(USER_ID).build())
                .build();
        List<EURegulationEntity> regulations = List.of(
                this.getEURegulation(EURegulationSeederForDev.REFERENCE_NUMBER_0));
        return new ComplianceAssessmentEntity(assessment, regulations);
    }

    private ComplianceAssessmentEntity assessment1() {
        ComplianceAssessment assessment = ComplianceAssessment.builder()
                .id(ID_1)
                .responsibleLawyer("Miguel Torres")
                .assessmentDate(LocalDate.of(2025, 2, 20))
                .nextReviewDate(LocalDate.of(2025, 8, 20))
                .correctiveActions("Document AI risk classification and oversight.")
                .supportingDocumentation("AI governance review")
                .notes("Assessment of digital technology obligations")
                .aiGenerated(true)
                .complianceLevel(ComplianceLevel.PENDING_REVIEW)
                .riskLevel(RiskLevel.HIGH)
                .userSnapshot(UserSnapshot.builder().id(USER_ID).build())
                .build();
        List<EURegulationEntity> regulations = List.of(
                this.getEURegulation(EURegulationSeederForDev.REFERENCE_NUMBER_1),
                this.getEURegulation(EURegulationSeederForDev.REFERENCE_NUMBER_2));
        return new ComplianceAssessmentEntity(assessment, regulations);
    }

    private ComplianceAssessmentEntity assessment2() {
        ComplianceAssessment assessment = ComplianceAssessment.builder()
                .id(ID_2)
                .responsibleLawyer("Laura García")
                .assessmentDate(LocalDate.now())
                .complianceDeadline(LocalDate.now().plusDays(10))
                .correctiveActions("Maintain evidence of GDPR compliance controls.")
                .supportingDocumentation("GDPR compliance review")
                .notes("Compliant low-risk follow-up assessment")
                .aiGenerated(true)
                .complianceLevel(ComplianceLevel.COMPLIANT)
                .riskLevel(RiskLevel.LOW)
                .userSnapshot(UserSnapshot.builder().id(USER_ID).build())
                .build();
        List<EURegulationEntity> regulations = List.of(
                this.getEURegulation(EURegulationSeederForDev.REFERENCE_NUMBER_0));
        return new ComplianceAssessmentEntity(assessment, regulations);
    }

    private ComplianceAssessmentEntity assessment3() {
        ComplianceAssessment assessment = ComplianceAssessment.builder()
                .id(ID_3)
                .responsibleLawyer("Miguel Torres")
                .assessmentDate(LocalDate.now())
                .complianceDeadline(LocalDate.now().plusDays(5))
                .correctiveActions("Address outstanding GDPR control deficiencies.")
                .supportingDocumentation("GDPR remediation review")
                .notes("Non-compliant high-risk assessment")
                .aiGenerated(false)
                .complianceLevel(ComplianceLevel.NON_COMPLIANT)
                .riskLevel(RiskLevel.HIGH)
                .userSnapshot(UserSnapshot.builder().id(USER_ID_1).build())
                .build();
        List<EURegulationEntity> regulations = List.of(
                this.getEURegulation(EURegulationSeederForDev.REFERENCE_NUMBER_0));
        return new ComplianceAssessmentEntity(assessment, regulations);
    }

    private ComplianceAssessmentEntity assessment4() {
        ComplianceAssessment assessment = ComplianceAssessment.builder()
                .id(ID_4)
                .responsibleLawyer("Miguel Torres")
                .assessmentDate(LocalDate.now())
                .correctiveActions("Continue monitoring AI governance obligations.")
                .supportingDocumentation("AI Act compliance review")
                .notes("Compliant low-risk AI Act assessment")
                .aiGenerated(true)
                .complianceLevel(ComplianceLevel.COMPLIANT)
                .riskLevel(RiskLevel.LOW)
                .userSnapshot(UserSnapshot.builder().id(USER_ID_1).build())
                .build();
        List<EURegulationEntity> regulations = List.of(
                this.getEURegulation(EURegulationSeederForDev.REFERENCE_NUMBER_1));
        return new ComplianceAssessmentEntity(assessment, regulations);
    }

    private EURegulationEntity getEURegulation(String officialReferenceNumber) {
        return this.euRegulationRepository.findByOfficialReferenceNumber(officialReferenceNumber)
                .orElseThrow(() -> new IllegalStateException(
                        "EU regulation required by compliance assessment seeder is missing: "
                                + officialReferenceNumber));
    }
}
