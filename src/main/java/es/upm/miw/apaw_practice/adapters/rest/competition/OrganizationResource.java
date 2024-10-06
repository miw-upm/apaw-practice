package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import es.upm.miw.apaw_practice.domain.services.competition.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OrganizationResource.ORGANIZATION)
public class OrganizationResource {

    static final String ORGANIZATION = "/competition/organization";
    static final String ID_ID = "/{id}";

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationResource(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PatchMapping(ID_ID)
    public Organization updateInternational(@PathVariable String id) {
        return this.organizationService.updateInternational(id);
    }
}
