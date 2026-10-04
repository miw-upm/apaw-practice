package es.upm.miw.apaw.adapters.in.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.OverdueComplianceReport;
import es.upm.miw.apaw.domain.services.euregulation.ComplianceDeadlineReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ComplianceDeadlineReportResource.REPORTS)
@RequiredArgsConstructor
public class ComplianceDeadlineReportResource {
    public static final String REPORTS = "/reports";
    public static final String COMPLIANCE_DEADLINES = "/compliance-deadlines";

    private final ComplianceDeadlineReportService complianceDeadlineReportService;

    @GetMapping(COMPLIANCE_DEADLINES)
    public List<OverdueComplianceReport> findReport() {
        return this.complianceDeadlineReportService.findReport();
    }
}
