package es.upm.miw.apaw_practice.domain.services.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.DiseaseUpdate;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.DiseasePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {

    private final DiseasePersistence diseasePersistence;

    @Autowired
    public DiseaseService(DiseasePersistence diseasePersistence) {
        this.diseasePersistence = diseasePersistence;
    }

    public void updateDiseases(List<DiseaseUpdate> diseaseUpdates) {
        diseaseUpdates.forEach(diseaseUpdate -> {
            this.diseasePersistence.updateDescription(diseaseUpdate.getAlias(),diseaseUpdate.getDescription());
        });
    }
}
