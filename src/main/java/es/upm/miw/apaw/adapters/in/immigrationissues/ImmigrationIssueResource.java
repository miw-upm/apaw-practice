package es.upm.miw.apaw.adapters.in.immigrationissues;

import es.upm.miw.apaw.domain.model.immigrationissues.CreationImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.services.immigrationissues.ImmigrationIssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ImmigrationIssueResource.IMMIGRATION_ISSUES)
@RequiredArgsConstructor
public class ImmigrationIssueResource {

    public static final String IMMIGRATION_ISSUES = "/immigration-issues";

    private final ImmigrationIssueService immigrationIssueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImmigrationIssue create(@Valid @RequestBody CreationImmigrationIssue creation) {
        return this.immigrationIssueService.create(creation);
    }
}