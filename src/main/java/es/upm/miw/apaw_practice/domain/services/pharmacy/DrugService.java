package es.upm.miw.apaw_practice.domain.services.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.DrugPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DrugService {

    private final DrugPersistence drugPersistence;

    @Autowired
    public DrugService(DrugPersistence drugPersistence) {
        this.drugPersistence = drugPersistence;
    }

    public Stream<Drug> getDrugs() {
        return this.drugPersistence.readAll();
    }
}
