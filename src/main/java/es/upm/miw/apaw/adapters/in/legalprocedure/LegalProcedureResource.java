package es.upm.miw.apaw.adapters.in.legalprocedure;

import es.upm.miw.apaw.domain.models.legalprocedure.CreationLegalProcedure;
import es.upm.miw.apaw.domain.models.legalprocedure.LegalProcedure;
import es.upm.miw.apaw.domain.services.legalprocedure.LegalProcedureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LegalProcedureResource.LEGAL_PROCEDURES)
@RequiredArgsConstructor
public class LegalProcedureResource {
    public static final String LEGAL_PROCEDURES = "/legal-procedures";

    private final LegalProcedureService legalProcedureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LegalProcedure create(@Valid @RequestBody CreationLegalProcedure creation) {
        return this.legalProcedureService.create(creation);
    }
}
