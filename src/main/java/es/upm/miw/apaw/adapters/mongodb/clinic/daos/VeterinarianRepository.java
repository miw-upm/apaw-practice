package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.VeterinarianEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VeterinarianRepository extends MongoRepository<VeterinarianEntity, String> {

    Optional<VeterinarianEntity> findByLicenseNumber(Long licenseNumber);
    boolean existsByLicenseNumber(Long licenseNumber);
    void deleteByLicenseNumber(Long licenseNumber);

}