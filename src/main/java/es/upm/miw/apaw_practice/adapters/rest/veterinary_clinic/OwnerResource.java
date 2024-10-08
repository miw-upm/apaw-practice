package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Owner;
import es.upm.miw.apaw_practice.domain.services.veterinary_clinic.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OwnerResource.OWNERS)
public class OwnerResource {

    static final String OWNERS = "/veterinary-clinic/owners";

    private final OwnerService ownerService;

    @Autowired
    public OwnerResource(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public Owner create(@RequestBody Owner owner) {
        owner.doDefault();
        return this.ownerService.create(owner);
    }

}