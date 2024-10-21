package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.domain.models.company.Company;
import es.upm.miw.apaw_practice.domain.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(CompanyResource.COMPANIES)
public class CompanyResource {
    static final String COMPANIES = "/company/companies";
    static final String SEARCH = "/search";
    static final String INDUSTRY = "/industry";
    static final String LOCATION_HIGHEST_EXPENSE = "/{location}/highest-expense";
    static final String MANAGEMENT_SEARCH = "/management-search";

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

    @GetMapping(LOCATION_HIGHEST_EXPENSE)
    public ResponseEntity<BigDecimal> getHighestExpenseAmountByLocation(@PathVariable String location) {
        BigDecimal highestExpenseAmount = this.companyService.findHighestExpenseAmountByLocation(location);
        return ResponseEntity.ok(highestExpenseAmount);
    }
    @GetMapping(MANAGEMENT_SEARCH)
    public ResponseEntity<List<String>> findManagementNamesByIndustryAndDescription(@RequestParam String industry,
                                                                                    @RequestParam String description) {
        List<String> managementNames = this.companyService.findManagementNamesByIndustryAndDescription(industry, description);
        return ResponseEntity.ok(managementNames);
    }
}