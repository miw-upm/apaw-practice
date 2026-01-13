package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.VeterinarianEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeterinarianRepository extends MongoRepository<VeterinarianEntity, String> {

    Optional<VeterinarianEntity> findByLicenseNumber(Long licenseNumber);
    boolean existsByLicenseNumber(Long licenseNumber);
    void deleteByLicenseNumber(Long licenseNumber);

    @Query("{ 'appointments': { $in: ?0 } }")
    List<VeterinarianEntity> findByAppointmentIds(List<UUID> appointmentIds);
}