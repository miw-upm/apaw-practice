package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.TyreRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Tyre;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.TyrePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("tyrePersistence")
public class TyrePersistenceMongodb implements TyrePersistence {

    private final TyreRepository tyreRepository;

    @Autowired
    public TyrePersistenceMongodb(TyreRepository tyreRepository) {
        this.tyreRepository = tyreRepository;
    }

    @Override
    public void deleteManufacturer(String manufacturer) {
        this.tyreRepository.deleteByManufacturer(manufacturer);
    }

    @Override
    public Tyre read(String model){
        return this.tyreRepository
                .findByModel(model)
                .orElseThrow(() -> new NotFoundException("Tyre model: " + model))
                .toTyre();
    };

    @Override
    public void create(Tyre tyre){
        this.tyreRepository
                .save(new TyreEntity(tyre));
    }

}