package es.upm.miw.apaw.adapters.resources.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.services.sports.academy.LegalGuardianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(LegalGuardianResource.LEGAL_GUARDIANS)
public class LegalGuardianResource {
    public static final String LEGAL_GUARDIANS = "/sports-academy/legal-guardians";
    public static final String ID_ID = "/{id}";
    private final LegalGuardianService legalGuardianService;

    @Autowired
    public LegalGuardianResource(LegalGuardianService legalGuardianService) {
        this.legalGuardianService = legalGuardianService;
    }

    @PutMapping(ID_ID)
    public LegalGuardian updateLegalGuardian(@Valid @PathVariable UUID id, @RequestBody LegalGuardian legalGuardian) {
        return this.legalGuardianService.update(id, legalGuardian);
    }
}
