package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.PetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface PetRepository extends MongoRepository<PetEntity, String> {
    Optional<PetEntity> findPetByChip(Integer chip);
    Optional<PetEntity> findPetByNickAndOwner(String nick, String owner);
    int deleteByNickAndOwner(String nick, String owner);
}
