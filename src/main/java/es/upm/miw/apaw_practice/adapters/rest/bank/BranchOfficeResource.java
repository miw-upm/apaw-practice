package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.services.bank.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BranchOfficeResource.OFFICES)
public class BranchOfficeResource {

    static final String OFFICES = "/bank/offices";


    private final BranchOfficeService branchOfficeService;

    @Autowired
    public BranchOfficeResource(BranchOfficeService branchOfficeService) {
        this.branchOfficeService = branchOfficeService;
    }

    @PostMapping
    public BranchOffice create(@RequestBody BranchOffice branchOffice) {
        branchOffice.doDefault();
        return this.branchOfficeService.create(branchOffice);
    }

}