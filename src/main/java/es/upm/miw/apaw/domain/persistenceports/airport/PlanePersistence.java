package es.upm.miw.apaw.domain.persistenceports.airport;

import es.upm.miw.apaw.domain.models.airport.Plane;

public interface PlanePersistence {
    Plane create(Plane plane);
    Plane update(String registrationNumber, Plane plane);
    Plane findByRegistrationNumber(String registrationNumber);
    boolean existRegistrationNumber(String registrationNumber);
}
