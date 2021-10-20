package es.upm.miw.apaw_practice.domain.services.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.DispensingPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.PharmacyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {

    private final PharmacyPersistence pharmacyPersistence;

    @Autowired
    public PharmacyService(PharmacyPersistence pharmacyPersistence) {
        this.pharmacyPersistence = pharmacyPersistence;
    }

    public void createPharmacy(Pharmacy pharmacy) {
        this.pharmacyPersistence.create(pharmacy);
    }
}
