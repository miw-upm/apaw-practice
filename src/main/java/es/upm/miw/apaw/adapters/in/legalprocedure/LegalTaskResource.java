package es.upm.miw.apaw.adapters.in.legalprocedure;

import es.upm.miw.apaw.domain.models.legalprocedure.LegalTask;
import es.upm.miw.apaw.domain.services.legalprocedure.LegalTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LegalTaskResource.LEGAL_TASKS)
@RequiredArgsConstructor
public class LegalTaskResource {
    public static final String LEGAL_TASKS = "/legal-tasks";

    private final LegalTaskService legalTaskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LegalTask create(@Valid @RequestBody LegalTask legalTask) {
        return this.legalTaskService.create(legalTask);
    }
}
