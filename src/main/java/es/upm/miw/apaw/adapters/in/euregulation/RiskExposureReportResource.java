package es.upm.miw.apaw.adapters.in.euregulation;

import es.upm.miw.apaw.domain.model.euregulation.RiskExposureReport;
import es.upm.miw.apaw.domain.services.euregulation.RiskExposureReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RiskExposureReportResource.REPORTS)
@RequiredArgsConstructor
public class RiskExposureReportResource {
    public static final String REPORTS = "/reports";
    public static final String RISK_EXPOSURE = "/risk-exposure";

    private final RiskExposureReportService riskExposureReportService;

    @GetMapping(RISK_EXPOSURE)
    public List<RiskExposureReport> findReport() {
        return this.riskExposureReportService.findReport();
    }
}
