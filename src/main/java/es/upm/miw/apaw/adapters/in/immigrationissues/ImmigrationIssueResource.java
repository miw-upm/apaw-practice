package es.upm.miw.apaw.adapters.in.immigrationissues;

import es.upm.miw.apaw.domain.model.immigrationissues.CreationImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssueFindCriteria;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport;
import es.upm.miw.apaw.domain.services.immigrationissues.ImmigrationIssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ImmigrationIssueResource.IMMIGRATION_ISSUES)
@RequiredArgsConstructor
public class ImmigrationIssueResource {

    public static final String IMMIGRATION_ISSUES = "/immigration-issues";
    public static final String REPORT = "/report";

    private final ImmigrationIssueService immigrationIssueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImmigrationIssue create(@Valid @RequestBody CreationImmigrationIssue creation) {
        return this.immigrationIssueService.create(creation);
    }

    @GetMapping
    public List<ImmigrationIssue> find(@ModelAttribute ImmigrationIssueFindCriteria criteria) {
        return this.immigrationIssueService.find(criteria);
    }

    @GetMapping(REPORT)
    public List<LawBasisUsageReport> findLawBasisUsageReport() {
        return this.immigrationIssueService.findLawBasisUsageReport();
    }
}