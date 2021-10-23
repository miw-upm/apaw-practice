package es.upm.miw.apaw_practice.domain.services.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.ActiveIngredientPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.PharmacyPersistence;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class ActiveIngredientService {

    private final ActiveIngredientPersistence activeIngredientPersistence;

    private final PharmacyPersistence pharmacyPersistence;


    public ActiveIngredientService(ActiveIngredientPersistence activeIngredientPersistence, PharmacyPersistence pharmacyPersistence) {
        this.activeIngredientPersistence = activeIngredientPersistence;
        this.pharmacyPersistence = pharmacyPersistence;
    }

    public Stream<ActiveIngredient> findByPharmacyAndByRepetition(String pharmacyRegistrationNumber, Boolean repeated) {
        Stream<ActiveIngredient> activeIngredientsByPharmacy = this.pharmacyPersistence.read(pharmacyRegistrationNumber).getDrugs().stream()
                .flatMap(drugs -> this.activeIngredientPersistence.readByDrugBarcode(drugs.getBarcode()));
        if (!repeated) {
            Set<List<String>> allComponents = new HashSet<>();
            activeIngredientsByPharmacy = activeIngredientsByPharmacy
                    .filter(activeIngredient -> !allComponents.add(activeIngredient.getComponents()));
        }
        return activeIngredientsByPharmacy;
    }
}
