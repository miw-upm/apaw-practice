package es.upm.miw.apaw_practice.domain.services.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import es.upm.miw.apaw_practice.domain.models.zoo.CageCaretakerSurname;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.AnimalPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.CagePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;

@Service
public class AnimalService {

    private final AnimalPersistence animalPersistence;
    private final CagePersistence cagePersistence;

    @Autowired
    public AnimalService(AnimalPersistence animalPersistence, CagePersistence cagePersistence) {
        this.animalPersistence = animalPersistence;
        this.cagePersistence = cagePersistence;
    }

    public void delete(Animal animal) {
        this.animalPersistence.delete(animal);
    }

    public Stream<CageCaretakerSurname> findCaretakersSurnamesByAnimalName(String name) {
        return this.cagePersistence.findAllContainingAny(this.animalPersistence.findByName(name))
                .map(cage -> new CageCaretakerSurname(cage.getLocationCode(), cage.getCaretaker().getSurname()));
    }
}
