package es.upm.miw.apaw.adapters.in.credentials;

import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.model.credentials.CreationCredential;
import es.upm.miw.apaw.domain.model.credentials.CredentialFindCriteria;
import es.upm.miw.apaw.domain.model.credentials.CredentialVerificationReport;
import es.upm.miw.apaw.domain.services.credentials.CredentialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CredentialResource.CREDENTIALS)
@RequiredArgsConstructor
public class CredentialResource {

    public static final String CREDENTIALS = "/credentials";
    public static final String REPORT = "/report";

    private final CredentialService credentialService;

    @GetMapping
    public List<Credential> find(@ModelAttribute CredentialFindCriteria criteria) {
        return this.credentialService.find(criteria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Credential create(@Valid @RequestBody CreationCredential creation) {
        return this.credentialService.create(creation);
    }

    @GetMapping(REPORT)
    public List<CredentialVerificationReport> findVerificationReport() {
        return this.credentialService.findVerificationReport();
    }
}