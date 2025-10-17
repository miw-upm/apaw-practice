package es.upm.miw.apaw.adapters.mongodb.vehicle.persistence;

import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.ExtraRepository;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.ExtraEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.vehicle.Extra;
import es.upm.miw.apaw.domain.persistenceports.vehicle.ExtraPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("extraPersistence")
public class ExtraPersistenceMongodb implements ExtraPersistence {

    private final ExtraRepository extraRepository;

    @Autowired
    public ExtraPersistenceMongodb(ExtraRepository extraRepository) {
        this.extraRepository = extraRepository;
    }

    @Override
    public Extra readByCategoryAndDescription(String category, String description) {
        return this.extraRepository
                .findByCategoryAndDescription(category, description)
                .orElseThrow(() -> new NotFoundException("Extra not found with category: " + category + " and description: " + description))
                .toExtra();
    }

    @Override
    public Extra update(Extra extra) {
        ExtraEntity extraEntity = this.extraRepository
                .findByCategoryAndDescription(extra.getCategory(), extra.getDescription())
                .orElseThrow(() -> new NotFoundException("Extra not found with category: " + extra.getCategory() + " and description: " + extra.getDescription()));
        extraEntity.setPrice(extra.getPrice());
        return this.extraRepository.save(extraEntity).toExtra();
    }

    @Override
    public void delete(UUID id) {
        this.extraRepository.deleteById(id);
    }

}
