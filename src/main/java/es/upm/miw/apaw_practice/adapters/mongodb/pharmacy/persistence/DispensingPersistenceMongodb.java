package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.ActiveIngredientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.DispensingRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.ActiveIngredientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DispensingEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.DispensingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("dispensingPersistence")
public class DispensingPersistenceMongodb implements DispensingPersistence {

    private final DispensingRepository dispensingRepository;

    private final ActiveIngredientRepository activeIngredientRepository;

    @Autowired
    public DispensingPersistenceMongodb(DispensingRepository dispensingRepository, ActiveIngredientRepository activeIngredientRepository) {
        this.dispensingRepository = dispensingRepository;
        this.activeIngredientRepository = activeIngredientRepository;
    }

    @Override
    public Dispensing update(Dispensing dispensing) {
        if(dispensing.getId() == null){
            throw new NotFoundException("The dispensing id can't be null");
        }
        DispensingEntity dispensingEntity = this.dispensingRepository
                .findById(dispensing.getId())
                .orElseThrow(() -> new NotFoundException("Dispensing id:" + dispensing.getId()));
        List<ActiveIngredientEntity> activeIngredientEntities = dispensing.getActiveIngredients().stream()
                .map(activeIngredient -> this.activeIngredientRepository.findByCode(activeIngredient.getCode())
                        .orElseThrow(() -> new NotFoundException("Active Ingredient code:" + activeIngredient.getCode())))
                .collect(Collectors.toList());

        dispensingEntity.setActiveIngredientEntities(activeIngredientEntities);
        dispensingEntity.setDispensingTimestamp(dispensing.getDispensingTimestamp());
        return this.dispensingRepository.save(dispensingEntity).toDispensing();
    }

    @Override
    public Dispensing readById(String id) {
        return this.dispensingRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Dispensing id:" + id))
                .toDispensing();
    }

    @Override
    public Stream<Dispensing> readAll() {
        return this.dispensingRepository
                .findAll().stream()
                .map(DispensingEntity::toDispensing);
    }

    @Override
    public void delete(String id) {
        this.dispensingRepository.deleteById(id);
    }

}