package es.upm.miw.apaw_practice.domain.persistence_ports.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Plan;

public interface PlanPersistence {
    Plan create(Plan plan);
}
