package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.services.vet_clinic.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(PetResource.PETS)
public class PetResource {
    static final String PETS = "/vet-clinic/pets";
    static final String NICKANDOWNER = "/{nick}/{owner}";
    static final String CHIP = "/{chip}";
    static final String DIAGNOSIS = "/diagnosis";

    private final PetService petService;

    @Autowired
    public PetResource(PetService petService) {
        this.petService = petService;
    }

    @DeleteMapping(NICKANDOWNER)
    public void delete(@PathVariable String nick, @PathVariable String owner) {
        this.petService.delete(nick, owner);
    }

    @PutMapping(CHIP + DIAGNOSIS)
    public Pet update(@PathVariable Integer chip, @RequestBody List<Diagnosis> diagnosisList) {
        return this.petService.updateDiagnosis(chip, diagnosisList);
    }
}
