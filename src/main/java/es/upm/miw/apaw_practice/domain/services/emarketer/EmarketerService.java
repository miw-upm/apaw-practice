package es.upm.miw.apaw_practice.domain.services.emarketer;

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

}
