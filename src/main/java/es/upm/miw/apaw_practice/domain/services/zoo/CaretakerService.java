package es.upm.miw.apaw_practice.domain.services.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.CaretakerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaretakerService {

    private final CaretakerPersistence caretakerPersistence;

    @Autowired
    public CaretakerService(CaretakerPersistence caretakerPersistence) {
        this.caretakerPersistence = caretakerPersistence;
    }

    public Caretaker findByDni(String dni) {
        return this.caretakerPersistence.findByDni(dni);
    }
}
