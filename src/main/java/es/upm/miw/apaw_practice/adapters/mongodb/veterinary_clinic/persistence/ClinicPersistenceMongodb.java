package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.ClinicRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.OwnerClinicRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.ClinicEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.ClinicPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@Repository("clinicPersistence")
public class ClinicPersistenceMongodb implements ClinicPersistence {

    private final ClinicRepository clinicRepository;
    private final OwnerClinicRepository ownerRepository;

    @Autowired
    public ClinicPersistenceMongodb(ClinicRepository clinicRepository, OwnerClinicRepository ownerRepository) {
        this.clinicRepository = clinicRepository;
        this.ownerRepository = ownerRepository;
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

    @Override
    public Flux<String> findByOwnerPhoneDistincClinicName(String phone) {
        return Mono.justOrEmpty(ownerRepository.findByPhone(phone))
                .flatMapMany(ownerClinic ->
                        Flux.fromIterable(clinicRepository.findAll())
                                .filter(clinic -> clinic.getEmployeeEntities().stream()
                                        .flatMap(employee -> employee.getAnimalEntities().stream())
                                        .anyMatch(animal -> animal.getOwnerEntity().getPhone().equals(phone))
                                )
                                .map(ClinicEntity::getName)
                                .distinct()
                );
    }
}