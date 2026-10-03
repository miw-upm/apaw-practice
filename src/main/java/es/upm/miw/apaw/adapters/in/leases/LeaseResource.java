package es.upm.miw.apaw.adapters.in.leases;

import es.upm.miw.apaw.domain.model.leases.CreationLease;
import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.model.leases.LeaseAmendmentReport;
import es.upm.miw.apaw.domain.model.leases.LeaseFindCriteria;
import es.upm.miw.apaw.domain.services.leases.LeaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(LeaseResource.LEASES)
@RequiredArgsConstructor
public class LeaseResource {
    public static final String LEASES = "/leases";
    public static final String REPORT = "/report";

    private final LeaseService leaseService;

    @GetMapping
    public List<Lease> find(@ModelAttribute LeaseFindCriteria criteria) {
        return this.leaseService.find(criteria);
    }

    @GetMapping(REPORT)
    public List<LeaseAmendmentReport> findAmendmentReport() {
        return this.leaseService.findAmendmentReport();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lease create(@Valid @RequestBody CreationLease creation) {
        return this.leaseService.create(creation);
    }
}
