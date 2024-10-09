package es.upm.miw.apaw_practice.domain.services.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.OwnerNightLifePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerNightLifeService {
    private final OwnerNightLifePersistence ownerPersistence;
    @Autowired
    public OwnerNightLifeService(OwnerNightLifePersistence ownerPersistence) {
        this.ownerPersistence = ownerPersistence;
    }
    public Owner create(Owner owner) {
        return this.ownerPersistence.create(owner);
    }
}
