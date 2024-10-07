package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.OwnerClinicRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.OwnerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.OwnerClinicPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ownerClinicPersistence")
public class OwnerClinicPersistenceMongodb implements OwnerClinicPersistence {

    private final OwnerClinicRepository ownerClinicRepository;

    @Autowired
    public OwnerClinicPersistenceMongodb(OwnerClinicRepository ownerClinicRepository) {
        this.ownerClinicRepository = ownerClinicRepository;
    }

    @Override
    public Owner read(String name) {
        return this.ownerClinicRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Owner name: " + name))
                .toOwner();
    }

    @Override
    public Owner create(Owner owner) {
        return this.ownerClinicRepository
                .save(new OwnerEntity(owner))
                .toOwner();
    }

    @Override
    public boolean existName(String name) {
        return this.ownerClinicRepository
                .findByName(name)
                .isPresent();
    }
}