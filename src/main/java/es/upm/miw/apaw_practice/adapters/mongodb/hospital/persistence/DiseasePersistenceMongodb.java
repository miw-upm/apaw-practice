package es.upm.miw.apaw_practice.adapters.mongodb.hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos.DiseaseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.DiseaseEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.DiseasePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("diseasePersistence")
public class DiseasePersistenceMongodb implements DiseasePersistence {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseasePersistenceMongodb(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public void updateDescription(String alias, String description) {
        DiseaseEntity diseaseEntity = this.diseaseRepository
                .findByAlias(alias).get();

        diseaseEntity.setDescription(description);

        this.diseaseRepository.save(diseaseEntity);
    }
}
