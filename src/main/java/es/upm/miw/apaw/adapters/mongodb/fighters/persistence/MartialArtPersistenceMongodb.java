package es.upm.miw.apaw.adapters.mongodb.fighters.persistence;

import es.upm.miw.apaw.adapters.mongodb.fighters.daos.MartialArtRepository;
import es.upm.miw.apaw.adapters.mongodb.fighters.entities.MartialArtEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import es.upm.miw.apaw.domain.persistenceports.fighters.MartialArtPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("martialArtPersistence")
public class MartialArtPersistenceMongodb implements MartialArtPersistence {
    private final MartialArtRepository martialArtRepository;

    @Autowired
    public MartialArtPersistenceMongodb(MartialArtRepository martialArtRepository){
        this.martialArtRepository = martialArtRepository;
    }

    @Override
    public MartialArt readByDiscipline(String discipline) {
        return this.martialArtRepository.findByDiscipline(discipline)
                .orElseThrow(() -> new NotFoundException("MartialArt discipline: " + discipline))
                .toMartialArt();
    }

    @Override
    public MartialArt update(MartialArt martialArt) {
        MartialArtEntity entity = this.martialArtRepository.findByDiscipline(martialArt.getDiscipline())
                .orElseThrow(() -> new NotFoundException("MartialArt discipline: " + martialArt.getDiscipline()));

        entity.setOrigin(martialArt.getOrigin());
        entity.setDescription(martialArt.getDescription());
        entity.setStriking(martialArt.getStriking());
        entity.setGrappling(martialArt.getGrappling());

        return this.martialArtRepository.save(entity).toMartialArt();
    }
}
