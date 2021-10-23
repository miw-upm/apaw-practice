package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.ActiveIngredientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.ActiveIngredientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.ActiveIngredientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("activeIngredientPersistence")
public class ActiveIngredientMongodb implements ActiveIngredientPersistence {

    private final ActiveIngredientRepository activeIngredientRepository;

    @Autowired
    public ActiveIngredientMongodb(ActiveIngredientRepository activeIngredientRepository) {
        this.activeIngredientRepository = activeIngredientRepository;
    }

    @Override
    public Stream<ActiveIngredient> readByDrugBarcode(String barcode) {
        DrugEntity drugEntity = new DrugEntity();
        drugEntity.setBarcode(barcode);
        return this.activeIngredientRepository
                .findByDrugEntity(drugEntity)
                .map(ActiveIngredientEntity::toActiveIngredient);
    }
}