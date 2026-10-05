package es.upm.miw.apaw.adapters.in.meeting;

import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.LegalIssueResolvedUpdate;
import es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport;
import es.upm.miw.apaw.domain.services.meeting.LegalIssueService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(LegalIssueResource.LEGAL_ISSUES)
@RequiredArgsConstructor
public class LegalIssueResource {
    public static final String LEGAL_ISSUES = "/legal-issues";
    public static final String ID = "/{id}";
    public static final String REPORT = "/report";

    private final LegalIssueService legalIssueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LegalIssue create(@Valid @RequestBody LegalIssue legalIssue) {
        return this.legalIssueService.create(legalIssue);
    }

    @GetMapping
    public List<LegalIssue> findAll() {
        return this.legalIssueService.findAll();
    }

    @GetMapping(REPORT)
    public List<MeetingParticipantReport> findParticipantReport() {
        return this.legalIssueService.findParticipantReport();
    }

    @GetMapping(ID)
    public LegalIssue read(@PathVariable UUID id) {
        return this.legalIssueService.read(id);
    }

    @PutMapping(ID)
    public LegalIssue update(@PathVariable UUID id, @Valid @RequestBody LegalIssue legalIssue) {
        return this.legalIssueService.update(id, legalIssue);
    }

    @PatchMapping
    public void updateResolvedStates(
            @RequestBody @NotEmpty List<@NotNull @Valid LegalIssueResolvedUpdate> updates) {
        this.legalIssueService.updateResolvedStates(updates);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.legalIssueService.delete(id);
    }
}
