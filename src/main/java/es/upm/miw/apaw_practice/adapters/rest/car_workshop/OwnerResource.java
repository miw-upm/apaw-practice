package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.services.car_workshop.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(OwnerResource.OWNERS)
public class OwnerResource {
    static final String OWNERS = "/car-workshop/owners";
    static final String DNI = "/{dni}";
    static final String SEARCH = "/search/{diameter}";

    private final OwnerService ownerService;

    @Autowired
    public OwnerResource(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(OwnerResource.DNI)
    public Owner readByDni(@PathVariable String dni) {
        return this.ownerService.readByDni(dni);
    }

    @GetMapping(OwnerResource.SEARCH)
    public Stream<String> findByTyreSpecificationDiameterGreaterThan(@PathVariable String diameter) {
        return this.ownerService.findByTyreSpecificationDiameterGreaterThan(Integer.parseInt(diameter));
    }
}
