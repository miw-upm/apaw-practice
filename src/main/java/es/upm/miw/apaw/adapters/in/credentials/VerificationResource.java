package es.upm.miw.apaw.adapters.in.credentials;

import es.upm.miw.apaw.domain.model.credentials.Verification;
import es.upm.miw.apaw.domain.model.credentials.VerificationPatch;
import es.upm.miw.apaw.domain.services.credentials.VerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(VerificationResource.VERIFICATIONS)
@RequiredArgsConstructor
public class VerificationResource {

    public static final String VERIFICATIONS = "/verifications";
    public static final String ID = "/{id}";

    private final VerificationService verificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Verification create(@Valid @RequestBody Verification verification) {
        return this.verificationService.create(verification);
    }

    @GetMapping
    public List<Verification> findAll() {
        return this.verificationService.findAll();
    }

    @GetMapping(ID)
    public Verification read(@PathVariable UUID id) {
        return this.verificationService.read(id);
    }

    @PutMapping(ID)
    public Verification update(@PathVariable UUID id,
                               @Valid @RequestBody Verification verification) {
        return this.verificationService.update(id, verification);
    }

    @PatchMapping(ID)
    public Verification patch(
            @PathVariable UUID id,
            @RequestBody VerificationPatch patch) {
        return this.verificationService.patch(id, patch);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.verificationService.delete(id);
    }
}