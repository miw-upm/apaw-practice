package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.AnimalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimalRepository extends MongoRepository<AnimalEntity, String> {

    // Método para buscar por la clave de negocio (microchipNumber)
    Optional<AnimalEntity> findByMicrochipNumber(Long microchipNumber);
}