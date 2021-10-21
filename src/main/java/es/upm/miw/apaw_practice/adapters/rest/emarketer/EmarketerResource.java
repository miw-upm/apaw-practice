package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import es.upm.miw.apaw_practice.domain.models.emarketer.Emarketer;
import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import es.upm.miw.apaw_practice.domain.services.emarketer.EmarketerService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping(EmarketerResource.EMARKETER)
public class EmarketerResource {

    static final String EMARKETER = "/emarketer/emarketers";

    static final String NAME = "/{name}";
    static final String CUPS = "/cups";
    static final String CUP = "/{cup}";
    static final String PLANS = "/plans";
    static final String DESCRIPTION = "/{description}";

    private final EmarketerService emarketerService;

    @Autowired
    public EmarketerResource(EmarketerService emarketerService) {
        this.emarketerService = emarketerService;
    }

    @DeleteMapping(EmarketerResource.NAME)
    public void delete(@PathVariable String name) {
        this.emarketerService.delete(name);
    }

    @GetMapping(EmarketerResource.CUPS + EmarketerResource.CUP)
    public BigDecimal getTotalPricePlanByCup(@PathVariable String cup) {
        return this.emarketerService.getTotalPricePlanByCup(cup)
                .map(Plan::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @GetMapping(EmarketerResource.PLANS + EmarketerResource.DESCRIPTION)
    public List<String> getDistinctCustomersNameListByEmarketerSystemic(@PathVariable String description) {
        return this.emarketerService.getCustomersNameListByEmarketerSystemic(description)
                .map(Customer::getName)
                .distinct()
                .collect(Collectors.toList());
    }
}
