package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.domain.models.company.Company;
import es.upm.miw.apaw_practice.domain.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CompanyResource.COMPANIES)
public class CompanyResource {
    static final String COMPANIES = "/company/companies";
    static final String SEARCH = "/search";
    static final String INDUSTRY = "/industry";

    private final CompanyService companyService;

    @Autowired
    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(SEARCH)
    public Company findByCompanyname(@RequestParam String companyname) {
        return this.companyService.findByCompanyname(companyname);
    }

    @PatchMapping(INDUSTRY)
    public void updateIndustry(@RequestParam String companyname, @RequestBody String newIndustry) {
        this.companyService.updateIndustry(companyname, newIndustry);
    }
}