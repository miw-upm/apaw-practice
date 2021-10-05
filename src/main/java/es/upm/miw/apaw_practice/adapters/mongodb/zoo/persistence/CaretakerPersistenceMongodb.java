package es.upm.miw.apaw_practice.adapters.mongodb.zoo.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.CaretakerRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.CaretakerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("caretakerPersistence")
public class CaretakerPersistenceMongodb implements CaretakerPersistence {

    private final CaretakerRepository caretakerRepository;

    @Autowired
    public CaretakerPersistenceMongodb(CaretakerRepository caretakerRepository) {
        this.caretakerRepository = caretakerRepository;
    }

    @Override
    public Caretaker findByDni(String dni) {
        return this.caretakerRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Caretaker with dni: " + dni))
                .toCaretaker();
    }
}
