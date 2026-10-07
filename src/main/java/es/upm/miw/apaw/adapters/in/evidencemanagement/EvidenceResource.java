package es.upm.miw.apaw.adapters.in.evidencemanagement;

import es.upm.miw.apaw.domain.model.evidencemanagement.CreationEvidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import es.upm.miw.apaw.domain.services.evidencemanagement.EvidenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EvidenceResource.EVIDENCES)
@RequiredArgsConstructor
public class EvidenceResource {
    public static final String EVIDENCES = "/evidences";

    private final EvidenceService evidenceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evidence create(@Valid @RequestBody CreationEvidence creation) {
        return this.evidenceService.create(creation);
    }
}
