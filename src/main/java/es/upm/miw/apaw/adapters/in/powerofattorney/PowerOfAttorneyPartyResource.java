package es.upm.miw.apaw.adapters.in.powerofattorney;

import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.services.powerofattorney.PowerOfAttorneyPartyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
@RequiredArgsConstructor
public class PowerOfAttorneyPartyResource {

    public static final String POWER_OF_ATTORNEY_PARTIES = "/power-of-attorney-parties";

    private final PowerOfAttorneyPartyService powerOfAttorneyPartyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PowerOfAttorneyParty create(@Valid @RequestBody CreationPowerOfAttorneyParty party) {
        return this.powerOfAttorneyPartyService.create(party);
    }
}
