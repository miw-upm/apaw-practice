package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos.StageRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.StagePersistence;
import org.springframework.stereotype.Repository;

@Repository("stagePersistence")
public class StagePersistenceMongodb implements StagePersistence {
    private final StageRepository stageRepository;

    public StagePersistenceMongodb(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    @Override
    public void delete(String name) {
        this.stageRepository.deleteByName(name);
    }

    @Override
    public Stage readByName(String name) {
        return this.stageRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Stage name:" + name)).toStage();
    }

}
