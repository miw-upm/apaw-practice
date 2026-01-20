package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.PetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PetRepository extends MongoRepository<PetEntity, String> {

    Optional<PetEntity> findByMicrochipNumber(Long microchipNumber);

    @Query("{ 'appointments': { $in: ?0 } }")
    List<PetEntity> findByAppointmentsIn(List<UUID> appointmentIds);
}
