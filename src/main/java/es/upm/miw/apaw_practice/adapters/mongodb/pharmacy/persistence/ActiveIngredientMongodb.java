package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.ActiveIngredientRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.ActiveIngredientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("activeIngredientPersistence")
public class ActiveIngredientMongodb implements ActiveIngredientPersistence {

    private final ActiveIngredientRepository activeIngredientRepository;

    @Autowired
    public ActiveIngredientMongodb(ActiveIngredientRepository activeIngredientRepository) {
        this.activeIngredientRepository = activeIngredientRepository;
    }

}