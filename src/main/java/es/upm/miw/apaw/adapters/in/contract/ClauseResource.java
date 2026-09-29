package es.upm.miw.apaw.adapters.in.contract;

import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.services.contract.ClauseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ClauseResource.CLAUSES)
@RequiredArgsConstructor
public class ClauseResource {

    public static final String CLAUSES = "/clauses";

    private final ClauseService clauseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Clause create(@Valid @RequestBody Clause clause) {
        return this.clauseService.create(clause);
    }
}