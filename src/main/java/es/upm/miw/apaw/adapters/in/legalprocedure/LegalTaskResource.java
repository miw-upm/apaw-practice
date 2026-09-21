package es.upm.miw.apaw.adapters.in.legalprocedure;

import es.upm.miw.apaw.domain.model.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.model.legalprocedure.LegalTaskStatusUpdate;
import es.upm.miw.apaw.domain.model.legalprocedure.LegalTaskUsageReport;
import es.upm.miw.apaw.domain.services.legalprocedure.LegalTaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(LegalTaskResource.LEGAL_TASKS)
@RequiredArgsConstructor
public class LegalTaskResource {
    public static final String LEGAL_TASKS = "/legal-tasks";
    public static final String ID = "/{id}";
    public static final String REPORT = "/report";

    private final LegalTaskService legalTaskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LegalTask create(@Valid @RequestBody LegalTask legalTask) {
        return this.legalTaskService.create(legalTask);
    }

    @GetMapping
    public List<LegalTask> findAll() {
        return this.legalTaskService.findAll();
    }

    @GetMapping(REPORT)
    public List<LegalTaskUsageReport> findUsageReport() {
        return this.legalTaskService.findUsageReport();
    }

    @GetMapping(ID)
    public LegalTask read(@PathVariable UUID id) {
        return this.legalTaskService.read(id);
    }

    @PutMapping(ID)
    public LegalTask update(@PathVariable UUID id, @Valid @RequestBody LegalTask legalTask) {
        return this.legalTaskService.update(id, legalTask);
    }

    @PatchMapping
    public void updateTaskStatuses(
            @RequestBody @NotEmpty List<@NotNull @Valid LegalTaskStatusUpdate> updates) {
        this.legalTaskService.updateTaskStatuses(updates);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.legalTaskService.delete(id);
    }
}
