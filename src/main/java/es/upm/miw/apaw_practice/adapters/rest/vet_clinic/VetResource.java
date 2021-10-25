package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import es.upm.miw.apaw_practice.domain.services.vet_clinic.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VetResource.VETS)
public class VetResource {
    static final String VETS = "/vet-clinic/vets";

    private final VetService vetService;

    @Autowired
    public VetResource(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping
    public void create(@RequestBody Vet vet) {
        this.vetService.create(vet);
    }

}
