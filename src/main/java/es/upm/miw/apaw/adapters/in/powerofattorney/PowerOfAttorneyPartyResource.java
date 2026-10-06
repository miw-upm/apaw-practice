package es.upm.miw.apaw.adapters.in.powerofattorney;

import es.upm.miw.apaw.domain.model.powerofattorney.CreationPowerOfAttorneyParty;
import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyParty;
import es.upm.miw.apaw.domain.services.powerofattorney.PowerOfAttorneyPartyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PowerOfAttorneyPartyResource.POWER_OF_ATTORNEY_PARTIES)
@RequiredArgsConstructor
public class PowerOfAttorneyPartyResource {

    public static final String POWER_OF_ATTORNEY_PARTIES = "/power-of-attorney-parties";

    public static final String ID = "/{id}";

    private final PowerOfAttorneyPartyService powerOfAttorneyPartyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PowerOfAttorneyParty create(@Valid @RequestBody CreationPowerOfAttorneyParty party) {
        return this.powerOfAttorneyPartyService.create(party);
    }

    @GetMapping(ID)
    public PowerOfAttorneyParty read(@PathVariable UUID id) {
        return this.powerOfAttorneyPartyService.read(id);
    }

    @PutMapping(ID)
    public PowerOfAttorneyParty update(@PathVariable UUID id, @Valid @RequestBody CreationPowerOfAttorneyParty party) {
        return this.powerOfAttorneyPartyService.update(id, party);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.powerOfAttorneyPartyService.delete(id);
    }

    @GetMapping
    public List<PowerOfAttorneyParty> findAll() {
        return this.powerOfAttorneyPartyService.findAll();
    }


}
