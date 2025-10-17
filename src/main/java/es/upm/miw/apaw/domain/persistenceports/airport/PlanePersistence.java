package es.upm.miw.apaw.domain.persistenceports.airport;

import es.upm.miw.apaw.domain.models.airport.Plane;

import java.util.stream.Stream;

public interface PlanePersistence {
    Plane create(Plane plane);
    Plane update(String registrationNumber, Plane plane);
    Stream<String> findRegistrationNumberByPilotMobile(String mobile);
    Plane findByRegistrationNumber(String registrationNumber);
    boolean existRegistrationNumber(String registrationNumber);
}
