package es.upm.miw.apaw.adapters.in.powerofattorney;

import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorney;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyFindCriteria;
import es.upm.miw.apaw.domain.services.powerofattorney.PowerOfAttorneyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PowerOfAttorneyResource.POWER_OF_ATTORNEYS)
@RequiredArgsConstructor
public class PowerOfAttorneyResource {

    public static final String POWER_OF_ATTORNEYS = "/power-of-attorneys";

    private final PowerOfAttorneyService powerOfAttorneyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PowerOfAttorney create(@Valid @RequestBody CreationPowerOfAttorney creation) {
        return this.powerOfAttorneyService.create(creation);
    }

    @GetMapping
    public List<PowerOfAttorney> find(@ModelAttribute PowerOfAttorneyFindCriteria criteria) {
        return this.powerOfAttorneyService.find(criteria);
    }
}
