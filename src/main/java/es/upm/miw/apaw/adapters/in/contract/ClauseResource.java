package es.upm.miw.apaw.adapters.in.contract;

import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.ClauseUpdate;
import es.upm.miw.apaw.domain.services.contract.ClauseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ClauseResource.CLAUSES)
@RequiredArgsConstructor
public class ClauseResource {

    public static final String CLAUSES = "/clauses";
    public static final String ID = "/{id}";

    private final ClauseService clauseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Clause create(@Valid @RequestBody Clause clause) {
        return this.clauseService.create(clause);
    }

    @GetMapping(ID)
    public Clause read(@PathVariable UUID id) {return this.clauseService.read(id);}

    @PutMapping(ID)
    public Clause update(@PathVariable UUID id, @Valid @RequestBody Clause clause) {return this.clauseService.update(id, clause);}

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {this.clauseService.delete(id);}

    @GetMapping
    public List<Clause> findAll() {return this.clauseService.findAll();}

    @PatchMapping(ID)
    public Clause patch(@PathVariable UUID id, @RequestBody ClauseUpdate patch) {
        return this.clauseService.patch(id, patch);
    }
}