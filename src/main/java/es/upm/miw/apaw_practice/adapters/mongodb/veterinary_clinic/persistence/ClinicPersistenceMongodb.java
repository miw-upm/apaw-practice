package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.ClinicRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.ClinicEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.ClinicPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@Repository("clinicPersistence")
public class ClinicPersistenceMongodb implements ClinicPersistence {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicPersistenceMongodb(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Clinic readByName(String name) {
        return this.clinicRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(" Clinic name? " + name))
                .toClinic();
    }

    @Override
    public void delete(String name) {
        this.clinicRepository.deleteByName(name);
    }

    @Override
    public Mono<BigDecimal> findByOwnerNameSumAge(String clinicName, String ownerName) {
        Optional<ClinicEntity> optionalClinic = this.clinicRepository.findByName(clinicName);
        return Mono.justOrEmpty(optionalClinic)
                .map(clinic -> {
                    int totalAge = clinic.getEmployeeEntities().stream()
                            .flatMap(employee -> employee.getAnimalEntities().stream()
                                    .filter(animal -> animal.getOwnerEntity().getName().equals(ownerName))
                                    .map(AnimalEntity::getAge)
                                    .distinct()
                            )
                            .reduce(0, Integer::sum);

                    return BigDecimal.valueOf(totalAge);
                })
                .defaultIfEmpty(BigDecimal.ZERO);
    }
}