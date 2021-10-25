package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw_practice.domain.services.pharmacy.ActiveIngredientService;
import es.upm.miw.apaw_practice.domain.services.pharmacy.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping(ActiveIngredientResource.ACTIVEINGREDIENTS)
public class ActiveIngredientResource {
    static final String ACTIVEINGREDIENTS = "/pharmacy/active-ingredients";

    static final String SEARCH = "/search";

    private final ActiveIngredientService activeIngredientService;

    @Autowired
    public ActiveIngredientResource(ActiveIngredientService activeIngredientService) {
        this.activeIngredientService = activeIngredientService;
    }

    @GetMapping(SEARCH)
    public Stream<ActiveIngredient> findByPharmacyRegistrationNumberAndByRepetition(@RequestParam String q) {
        String pharmacyRegistrationNumber = new LexicalAnalyzer().extractWithAssure(q, "pharmacy");
        Boolean repeated = new LexicalAnalyzer().extractWithAssure(q, "repeated", Boolean::new);
        return this.activeIngredientService.findByPharmacyAndByRepetition(pharmacyRegistrationNumber, repeated);
    }
}
