package es.upm.miw.apaw.domain.persistenceports.airport;

import es.upm.miw.apaw.domain.models.airport.Plane;

import java.util.List;

public interface PlanePersistence {
    Plane create(Plane plane);
    Plane update(String registrationNumber, Plane plane);
    List<String> findRegistrationNumberByPilotMobile(String mobile);
    Plane findByRegistrationNumber(String registrationNumber);
    boolean existRegistrationNumber(String registrationNumber);
}
