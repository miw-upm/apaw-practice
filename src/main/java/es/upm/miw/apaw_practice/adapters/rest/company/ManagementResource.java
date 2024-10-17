package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.domain.models.company.Management;
import es.upm.miw.apaw_practice.domain.services.company.ManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ManagementResource.MANAGEMENTS)
public class ManagementResource {
    static final String MANAGEMENTS = "/managements";
    private final ManagementService managementService;

    @Autowired
    public ManagementResource(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping
    public Management create(@RequestBody Management management) {
        return this.managementService.create(management);
    }
}