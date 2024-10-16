package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.domain.models.company.Company;
import es.upm.miw.apaw_practice.domain.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CompanyResource.COMPANIES)
public class CompanyResource {
    static final String COMPANIES = "/company/companies";
    static final String SEARCH = "/search";

    private final CompanyService companyService;

    @Autowired
    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(SEARCH)
    public Company findByCompanyname(@RequestParam String companyname) {
        return this.companyService.findByCompanyname(companyname);
    }
}