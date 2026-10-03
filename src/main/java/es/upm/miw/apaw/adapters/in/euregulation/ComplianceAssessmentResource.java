package es.upm.miw.apaw.adapters.in.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.services.euregulation.ComplianceAssessmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
@RequiredArgsConstructor
public class ComplianceAssessmentResource {
    public static final String COMPLIANCE_ASSESSMENTS = "/compliance-assessments";
    public static final String ID = "/{id}";

    private final ComplianceAssessmentService complianceAssessmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComplianceAssessment create(@Valid @RequestBody ComplianceAssessmentCreationDto creation) {
        return this.complianceAssessmentService.create(
                creation.toDomain(), creation.userId(), creation.euRegulationIds());
    }

    @GetMapping(ID)
    public ComplianceAssessment read(@PathVariable UUID id) {
        return this.complianceAssessmentService.read(id);
    }
}
