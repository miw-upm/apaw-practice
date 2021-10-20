package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.PharmacyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.PharmacyEntity;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.PharmacyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("pharmacyPersistence")
public class PharmacyPersistenceMongodb implements PharmacyPersistence {

    private final PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyPersistenceMongodb(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public Pharmacy create(Pharmacy drug) {
        return this.pharmacyRepository
                .save(new PharmacyEntity(drug))
                .toPharmacy();
    }

}