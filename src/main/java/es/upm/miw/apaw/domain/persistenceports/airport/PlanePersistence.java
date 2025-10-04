package es.upm.miw.apaw.domain.persistenceports.airport;

import es.upm.miw.apaw.domain.models.airport.Plane;

public interface PlanePersistence {
    Plane create(Plane plane);
    boolean existRegistrationNumber(String registrationNumber);
}
