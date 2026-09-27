package es.upm.miw.apaw.adapters.in.leases;

import es.upm.miw.apaw.domain.model.leases.CreationLease;
import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.services.leases.LeaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(LeaseResource.LEASES)
@RequiredArgsConstructor
public class LeaseResource {
    public static final String LEASES = "/leases";

    private final LeaseService leaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lease create(@Valid @RequestBody CreationLease creation) {
        return this.leaseService.create(creation);
    }
}
