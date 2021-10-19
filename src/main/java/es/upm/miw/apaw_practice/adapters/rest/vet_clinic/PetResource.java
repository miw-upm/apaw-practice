package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.domain.services.vet_clinic.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(PetResource.PETS)
public class PetResource {
    static final String PETS = "/vet-clinic/pets";
    static final String NICKANDOWNER = "/{nick}/{owner}";

    private final PetService petService;

    @Autowired
    public PetResource(PetService petService) {
        this.petService = petService;
    }

    @DeleteMapping(NICKANDOWNER)
    public void delete(@PathVariable String nick, @PathVariable String owner) {
        this.petService.delete(nick, owner);
    }
}
