package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.persistenceports.airport.PlanePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaneService {

    private final PlanePersistence planePersistence;

    @Autowired
    public PlaneService(PlanePersistence planePersistence) {
        this.planePersistence = planePersistence;
    }

    public Plane create(Plane plane) {
        return this.planePersistence.create(plane);
    }


}
