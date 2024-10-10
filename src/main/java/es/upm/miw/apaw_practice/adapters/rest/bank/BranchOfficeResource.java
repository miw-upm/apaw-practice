package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.services.bank.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(BranchOfficeResource.OFFICES)
public class BranchOfficeResource {

    static final String OFFICES = "/bank/offices";
    static final String BUILDING_NAME = "/{buildingName}}";
    static final String BALANCE = "/balance";


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

    @GetMapping(BUILDING_NAME + BALANCE)
    public BigDecimal getAssociatedBalance(@PathVariable String buildingName) {
        return this.branchOfficeService.getAssociatedBalance(buildingName);
    }

}