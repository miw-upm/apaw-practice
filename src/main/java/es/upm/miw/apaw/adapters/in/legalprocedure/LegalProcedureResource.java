package es.upm.miw.apaw.adapters.in.legalprocedure;

import es.upm.miw.apaw.domain.models.legalprocedure.CreationLegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedureFindCriteria;
import es.upm.miw.apaw.domain.services.legalprocedure.LegalProcedureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(LegalProcedureResource.LEGAL_PROCEDURES)
@RequiredArgsConstructor
public class LegalProcedureResource {
    public static final String LEGAL_PROCEDURES = "/legal-procedures";

    private final LegalProcedureService legalProcedureService;

    @GetMapping
    public List<LegalProcedure> find(@ModelAttribute LegalProcedureFindCriteria criteria) {
        return this.legalProcedureService.find(criteria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LegalProcedure create(@Valid @RequestBody CreationLegalProcedure creation) {
        return this.legalProcedureService.create(creation);
    }
}
