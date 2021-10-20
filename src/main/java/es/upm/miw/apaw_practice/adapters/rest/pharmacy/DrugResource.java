package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.services.pharmacy.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(DrugResource.DRUGS)
public class DrugResource {
    static final String DRUGS = "/pharmacy/drugs";

    private final DrugService drugService;

    @Autowired
    public DrugResource(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public Stream<Drug> getDrugs() {
        return this.drugService.getDrugs();
    }
}
