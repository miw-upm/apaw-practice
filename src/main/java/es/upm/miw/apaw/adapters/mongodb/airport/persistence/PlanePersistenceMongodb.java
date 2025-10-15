package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.PlaneRepository;
import es.upm.miw.apaw.adapters.mongodb.airport.entities.PlaneEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.persistenceports.airport.PlanePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("planePersistence")
public class PlanePersistenceMongodb implements PlanePersistence {

    private final PlaneRepository planeRepository;

    @Autowired
    public PlanePersistenceMongodb(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
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
                .findByRegistrationNumber(plane.getRegistrationNumber())
                .orElseThrow(() -> new NotFoundException("Plane registration number: " + plane.getRegistrationNumber()));
        planeEntity.fromPlane(plane);
        return this.planeRepository
                .save(planeEntity)
                .toPlane();
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
