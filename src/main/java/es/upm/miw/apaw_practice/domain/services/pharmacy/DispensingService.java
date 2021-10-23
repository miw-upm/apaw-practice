package es.upm.miw.apaw_practice.domain.services.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.DispensingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispensingService {

    private final DispensingPersistence dispensingPersistence;

    @Autowired
    public DispensingService(DispensingPersistence dispensingPersistence) {
        this.dispensingPersistence = dispensingPersistence;
    }

    public Dispensing updateDispensing(Dispensing dispensing) {
        return this.dispensingPersistence.update(dispensing);
    }

    public void deleteDispensing(String id) {
        this.dispensingPersistence.delete(id);
    }
}
