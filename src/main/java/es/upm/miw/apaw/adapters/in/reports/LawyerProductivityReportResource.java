package es.upm.miw.apaw.adapters.in.reports;

import es.upm.miw.apaw.domain.services.euregulation.ComplianceAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(LawyerProductivityReportResource.REPORTS)
@RequiredArgsConstructor
public class LawyerProductivityReportResource {
    public static final String REPORTS = "/reports";
    public static final String LAWYER_PRODUCTIVITY = "/lawyer-productivity";

    private final ComplianceAssessmentService complianceAssessmentService;

    @GetMapping(LAWYER_PRODUCTIVITY)
    public List<LawyerProductivityReportDto> findLawyerProductivityReport() {
        return this.complianceAssessmentService.findLawyerProductivityReport().stream()
                .map(LawyerProductivityReportDto::fromDomain)
                .toList();
    }
}
