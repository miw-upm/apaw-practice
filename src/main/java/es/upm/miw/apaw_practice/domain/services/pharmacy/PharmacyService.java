package es.upm.miw.apaw_practice.domain.services.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.*;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.DispensingPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.DrugPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.PharmacyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PharmacyService {

    private final PharmacyPersistence pharmacyPersistence;

    private final DrugPersistence drugPersistence;

    private final DispensingPersistence dispensingPersistence;

    @Autowired
    public PharmacyService(PharmacyPersistence pharmacyPersistence, DrugPersistence drugPersistence,
                           DispensingPersistence dispensingPersistence) {
        this.drugPersistence = drugPersistence;
        this.pharmacyPersistence = pharmacyPersistence;
        this.dispensingPersistence =dispensingPersistence;
    }

    public void createPharmacy(Pharmacy pharmacy) {
        this.pharmacyPersistence.create(pharmacy);
    }

    public void updateDrugs(Stream<PharmacyDrugsUpdating> pharmacyDrugsUpdatingList) {
        pharmacyDrugsUpdatingList.map(pharmacyNewDrugList -> {
                    Pharmacy pharmacy = this.pharmacyPersistence.readByRegistrationNumber(pharmacyNewDrugList.getRegistrationNumber());
                    pharmacy.setDrugs(pharmacyNewDrugList.getDrugs());
                    return pharmacy;
                })
                .forEach(pharmacy -> this.pharmacyPersistence.update(pharmacy.getRegistrationNumber(), pharmacy));
    }

    public Stream<Pharmacy> findByDispensing(String dispensing) {

        List<String> barcodes =  this.dispensingPersistence.readById(dispensing).getActiveIngredients().stream()
                .map(activeIngredient -> this.drugPersistence.readByBarcode(activeIngredient.getDrug().getBarcode()))
                .map(Drug::getBarcode)
                .collect(Collectors.toList());
                return this.pharmacyPersistence.readAll()
                             .filter(pharmacy -> pharmacy.getDrugs().stream()
                        .map(Drug::getBarcode)
                        .anyMatch(barcodes::contains));
    }
}