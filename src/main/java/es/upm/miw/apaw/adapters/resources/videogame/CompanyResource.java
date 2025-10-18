package es.upm.miw.apaw.adapters.resources.videogame;

import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.services.videogame.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(CompanyResource.COMPANY)
public class CompanyResource {
    public static final String COMPANY = "/videogame/company";
    private final CompanyService companyService;

    @Autowired
    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Company create(@Valid @RequestBody Company company) {
        company.doDefault();
        return this.companyService.create(company);
    }

}
