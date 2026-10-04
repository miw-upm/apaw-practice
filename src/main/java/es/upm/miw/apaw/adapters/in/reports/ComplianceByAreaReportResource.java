package es.upm.miw.apaw.adapters.in.reports;

import es.upm.miw.apaw.domain.services.euregulation.ComplianceAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ComplianceByAreaReportResource.REPORTS)
@RequiredArgsConstructor
public class ComplianceByAreaReportResource {
    public static final String REPORTS = "/reports";
    public static final String COMPLIANCE_BY_AREA = "/compliance-by-area";

    private final ComplianceAssessmentService complianceAssessmentService;

    @GetMapping(COMPLIANCE_BY_AREA)
    public List<ComplianceByAreaReportDto> findComplianceByAreaReport() {
        return this.complianceAssessmentService.findComplianceByAreaReport().stream()
                .map(ComplianceByAreaReportDto::fromDomain)
                .toList();
    }
}
