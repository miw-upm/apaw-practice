package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.FlightRepository;
import es.upm.miw.apaw.adapters.mongodb.airport.daos.PlaneRepository;
import es.upm.miw.apaw.adapters.mongodb.airport.entities.FlightEntity;
import es.upm.miw.apaw.adapters.mongodb.airport.entities.PlaneEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.persistenceports.airport.PlanePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("planePersistence")
public class PlanePersistenceMongodb implements PlanePersistence {

    private final PlaneRepository planeRepository;
    private final FlightRepository flightRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public PlanePersistenceMongodb(
            PlaneRepository planeRepository,
            FlightRepository flightRepository,
            UserRestClient userRestClient
    ) {
        this.planeRepository = planeRepository;
        this.flightRepository = flightRepository;
        this.userRestClient = userRestClient;
    }

    @Override
    public Plane create(Plane plane) {
        return this.planeRepository
                .save(new PlaneEntity(plane))
                .toPlane();
    }

    @Override
    public Plane update(String registrationNumber, Plane plane) {
        PlaneEntity planeEntity = this.planeRepository
                .findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new NotFoundException("Plane registration number: " + plane.getRegistrationNumber()));
        planeEntity.fromPlane(plane);
        return this.planeRepository
                .save(planeEntity)
                .toPlane();
    }

    @Override
    public Stream<String> findRegistrationNumberByPilotMobile(String mobile) {
        return this.flightRepository.findAll().stream()
                .filter(flight -> flight.getPilotId()
                        .equals(this.userRestClient.readByMobile(mobile).getId()))
                .map(FlightEntity::getPlane)
                .map(PlaneEntity::getRegistrationNumber)
                .distinct();
    }

    @Override
    public Plane findByRegistrationNumber(String registrationNumber) {
        return this.planeRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new NotFoundException(" Plane registration number: " + registrationNumber))
                .toPlane();
    }

    @Override
    public boolean existRegistrationNumber(String registrationNumber) {
        return this.planeRepository
                .findByRegistrationNumber(registrationNumber)
                .isPresent();
    }
}
