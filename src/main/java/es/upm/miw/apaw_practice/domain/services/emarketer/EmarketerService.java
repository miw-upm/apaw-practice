package es.upm.miw.apaw_practice.domain.services.emarketer;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.EmarketerEntity;
import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import es.upm.miw.apaw_practice.domain.models.emarketer.Emarketer;
import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.EmarketerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class EmarketerService {

    private final EmarketerPersistence emarketerPersistence;

    @Autowired
    public EmarketerService(EmarketerPersistence emarketerPersistence) {
        this.emarketerPersistence = emarketerPersistence;
    }

    public void delete(String name) {
        this.emarketerPersistence.delete(name);
    }

    public Stream<Emarketer> readByCups(String cups) {
        return this.emarketerPersistence.readByCups(cups);
    }

    public Stream<Plan> getTotalPricePlanByCup(String cups) {
        Stream<Emarketer> emarketers = this.readByCups(cups);
        return emarketers.flatMap(plan ->
                plan.getPlans().stream());
    }

    public Stream<Emarketer> readByPlanDescription(String description) {
        return this.emarketerPersistence.readByPlanDescription(description);
    }

    public Stream<Customer> getCustomersNameListByEmarketerSystemic(String description) {
        Stream<Emarketer> emarketers = this.readByPlanDescription(description);
        return emarketers
                .filter(emarketer -> emarketer.isSystemic().equals(true))
                .flatMap(cup ->
                cup.getCups().stream())
                .map(Cups::getCustomer);
    }

    public Stream<Emarketer> readAll() {
        return this.emarketerPersistence.readAll();
    }

}
