package es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface ClinicPersistence {

    Clinic readByName(String name);

    void delete(String name);

    Mono<BigDecimal> findByOwnerNameSumAge(String clinicName, String ownerName);
}