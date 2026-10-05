package es.upm.miw.apaw.adapters.in.credentials;

import es.upm.miw.apaw.domain.model.credentials.Credential;
import es.upm.miw.apaw.domain.model.credentials.CreationCredential;
import es.upm.miw.apaw.domain.services.credentials.CredentialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CredentialResource.CREDENTIALS)
@RequiredArgsConstructor
public class CredentialResource {

    public static final String CREDENTIALS = "/credentials";

    private final CredentialService credentialService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Credential create(@Valid @RequestBody CreationCredential creation) {
        return this.credentialService.create(creation);
    }
}