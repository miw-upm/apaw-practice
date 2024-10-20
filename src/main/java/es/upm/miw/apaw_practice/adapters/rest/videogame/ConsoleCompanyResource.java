package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompanyActivedUpdating;
import es.upm.miw.apaw_practice.domain.services.videogame.ConsoleCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ConsoleCompanyResource.CONSOLE_COMPANIES)
public class ConsoleCompanyResource {
    public static final String CONSOLE_COMPANIES = "/console-companies";
    static final String COMPANY_INFORMATION = "/{companyInformation}";
    private final ConsoleCompanyService consoleCompanyService;

    @Autowired
    public ConsoleCompanyResource(ConsoleCompanyService consoleCompanyService) {
        this.consoleCompanyService = consoleCompanyService;
    }

    @PatchMapping(COMPANY_INFORMATION)
    public ConsoleCompany updateActiveCompany(@PathVariable String companyInformation) {
        return this.consoleCompanyService.updateActiveCompany(companyInformation);
    }

    @PatchMapping
    public void updateAllCompanyActive(@RequestBody List<ConsoleCompanyActivedUpdating> consoleCompanyActivedUpdating) {
        this.consoleCompanyService.updateAllCompanyActive(consoleCompanyActivedUpdating.stream());
    }
}