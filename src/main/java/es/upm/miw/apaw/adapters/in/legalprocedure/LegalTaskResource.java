package es.upm.miw.apaw.adapters.in.legalprocedure;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.services.legalprocedure.LegalTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(LegalTaskResource.LEGAL_TASKS)
@RequiredArgsConstructor
public class LegalTaskResource {
    public static final String LEGAL_TASKS = "/legal-tasks";
    public static final String ID = "/{id}";

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

    @GetMapping(ID)
    public LegalTask read(@PathVariable UUID id) {
        return this.legalTaskService.read(id);
    }

    @PutMapping(ID)
    public LegalTask update(@PathVariable UUID id, @Valid @RequestBody LegalTask legalTask) {
        return this.legalTaskService.update(id, legalTask);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.legalTaskService.delete(id);
    }
}
