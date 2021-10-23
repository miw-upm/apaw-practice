package es.upm.miw.apaw_practice.domain.services.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.models.pharmacy.PharmacyDrugsUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.DrugPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.PharmacyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PharmacyService {

    private final PharmacyPersistence pharmacyPersistence;

    private final DrugPersistence drugPersistence;

    @Autowired
    public PharmacyService(PharmacyPersistence pharmacyPersistence, DrugPersistence drugPersistence) {
        this.drugPersistence = drugPersistence;
        this.pharmacyPersistence = pharmacyPersistence;
    }

    public void createPharmacy(Pharmacy pharmacy) {
        this.pharmacyPersistence.create(pharmacy);
    }

    public void updateDrugs(Stream<PharmacyDrugsUpdating> pharmacyDrugsUpdatingList) {
        pharmacyDrugsUpdatingList.map(pharmacyNewDrugList -> {
                    Pharmacy pharmacy = this.pharmacyPersistence.read(pharmacyNewDrugList.getRegistrationNumber());
                    pharmacy.setDrugs(pharmacyNewDrugList.getDrugs());
                    return pharmacy;
                })
                .forEach(pharmacy -> this.pharmacyPersistence.update(pharmacy.getRegistrationNumber(), pharmacy));
    }
}