package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.PharmacyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DispensingEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.PharmacyEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.PharmacyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("pharmacyPersistence")
public class PharmacyPersistenceMongodb implements PharmacyPersistence {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyPersistenceMongodb(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Pharmacy create(Pharmacy pharmacy) {
        return this.pharmacyRepository
                .save(new PharmacyEntity(pharmacy))
                .toPharmacy();
    }

    @Override
    public Pharmacy readByRegistrationNumber(String registrationNumber) {
        return this.pharmacyRepository
                .findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new NotFoundException("Pharmacy registration number: " + registrationNumber))
                .toPharmacy();
    }

    @Override
    public Stream<Pharmacy> readAll() {
        return this.pharmacyRepository
                .findAll().stream()
                .map(PharmacyEntity::toPharmacy);
    }

    @Override
    public Pharmacy update(String registrationNumber, Pharmacy pharmacy) {
        PharmacyEntity pharmacyEntity = this.pharmacyRepository
                .findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new NotFoundException("Pharmacy registration number: " + pharmacy.getRegistrationNumber()));
        pharmacyEntity.fromPharmacy(pharmacy);
        return this.pharmacyRepository
                .save(pharmacyEntity)
                .toPharmacy();
    }

}