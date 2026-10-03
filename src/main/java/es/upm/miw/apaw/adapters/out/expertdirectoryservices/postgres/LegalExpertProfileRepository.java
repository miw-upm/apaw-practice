package es.upm.miw.apaw.adapters.out.expertdirectoryservices.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LegalExpertProfileRepository extends JpaRepository<LegalExpertProfileEntity, UUID> {

    boolean existsByTaxIdCode(String taxIdCode);

    boolean existsByProfessionalLicense(String professionalLicense);
}