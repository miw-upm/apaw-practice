package es.upm.miw.apaw.adapters.in.copyright;

import es.upm.miw.apaw.domain.model.copyright.Claim;
import es.upm.miw.apaw.domain.model.copyright.ClaimCreation;
import es.upm.miw.apaw.domain.model.copyright.ClaimTaskStatusUpdate;
import es.upm.miw.apaw.domain.services.copyright.ClaimService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ClaimResource.CLAIMS)
@RequiredArgsConstructor
public class ClaimResource {
    public static final String CLAIMS = "/claims";
    public static final String ID = "/{id}";

    private final ClaimService claimService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Claim create(@Valid @RequestBody ClaimCreation claimCreation) {
        return this.claimService.create(claimCreation);
    }

    @GetMapping(ID)
    public Claim read(@PathVariable UUID id) {
        return this.claimService.read(id);
    }

    @PutMapping(ID)
    public Claim update(@PathVariable UUID id, @Valid @RequestBody Claim claim) {
        return this.claimService.update(id, claim);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.claimService.delete(id);
    }

    @GetMapping
    public List<Claim> findAll() {
        return this.claimService.findAll();
    }

    @PatchMapping
    public void updateTaskStatuses(
            @RequestBody @NotEmpty List<@NotNull @Valid ClaimTaskStatusUpdate> updates) {
        this.claimService.updateTaskStatuses(updates);
    }
}
