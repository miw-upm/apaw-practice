package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.domain.models.department.Company;
import es.upm.miw.apaw_practice.domain.services.department.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CompanyResource.COMPANIES)
public class CompanyResource {
    static final String COMPANIES = "/department/companies";

    static final String CIF_ID = "/{cif}";

    private final CompanyService companyService;

    @Autowired
    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PutMapping(CIF_ID)
    public Company update(@PathVariable String cif, @RequestBody Company company) {
        return this.companyService.update(cif, company);
    }
}
