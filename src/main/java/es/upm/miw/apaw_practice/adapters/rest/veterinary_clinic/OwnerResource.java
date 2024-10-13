package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import es.upm.miw.apaw_practice.domain.services.veterinary_clinic.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public OwnerClinic create(@RequestBody OwnerClinic ownerClinic) {
        ownerClinic.doDefault();
        return this.ownerService.create(ownerClinic);
    }

    @PatchMapping
    public void update(@RequestBody List<OwnerClinic> ownerClinicList) {
        this.ownerService.updateOwner(ownerClinicList.stream());
    }

}