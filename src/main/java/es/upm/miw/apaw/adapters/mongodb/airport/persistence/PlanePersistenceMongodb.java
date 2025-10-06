package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.PlaneRepository;
import es.upm.miw.apaw.adapters.mongodb.airport.entities.PlaneEntity;
import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.persistenceports.airport.PlanePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("airportPersistence")
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
    public boolean existRegistrationNumber(String registrationNumber) {
        return this.planeRepository
                .findByRegistrationNumber(registrationNumber)
                .isPresent();
    }
}
