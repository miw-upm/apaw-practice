package es.upm.miw.apaw_practice.domain.services.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.ClinicPersistence;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ClinicService {

    private final ClinicPersistence clinicPersistence;

    public ClinicService(ClinicPersistence clinicPersistence) {
        this.clinicPersistence = clinicPersistence;
    }

    public Clinic read(String name) {
        return this.clinicPersistence.readByName((name));
    }

    public void delete(String name) {
        this.clinicPersistence.delete(name);
    }

    public Mono<BigDecimal> findByOwnerNameSumAge(String clinicName, String ownerName) {
        return this.clinicPersistence.findByOwnerNameSumAge(clinicName, ownerName);
    }

    public Flux<String> findByOwnerPhoneDistincClinicName(String phone) {
        return this.clinicPersistence.findByOwnerPhoneDistincClinicName(phone);
    }
}