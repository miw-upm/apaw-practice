package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.OwnerClinicRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.OwnerClinicEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
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
    public OwnerClinic read(String name) {
        return this.ownerClinicRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("OwnerClinic name: " + name))
                .toOwner();
    }

    @Override
    public OwnerClinic update(String address, String phone, OwnerClinic ownerClinic) {
        OwnerClinicEntity ownerClinicEntity = this.ownerClinicRepository
                .findByName(ownerClinic.getName())
                .orElseThrow(() -> new NotFoundException("OwnerClinic name: " + ownerClinic.getName()));
        ownerClinicEntity.fromOwner(ownerClinic);
        return this.ownerClinicRepository
                .save(ownerClinicEntity)
                .toOwner();
    }

    @Override
    public OwnerClinic create(OwnerClinic ownerClinic) {
        return this.ownerClinicRepository
                .save(new OwnerClinicEntity(ownerClinic))
                .toOwner();
    }

    @Override
    public boolean existName(String name) {
        return this.ownerClinicRepository
                .findByName(name)
                .isPresent();
    }
}