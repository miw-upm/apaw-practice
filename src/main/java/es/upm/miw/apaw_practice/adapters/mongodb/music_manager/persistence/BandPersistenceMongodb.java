package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos.BandRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.ArtistEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.BandEntity;
import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_manager.BandPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("bandPersistence")
public class BandPersistenceMongodb implements BandPersistence {
    private final BandRepository bandRepository;

    @Autowired
    public BandPersistenceMongodb(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public Band create(Band band) {
        List<ArtistEntity> artistEntities = band.getArtists().stream()
                .map(ArtistEntity::new)
                .collect(Collectors.toList());

        return this.bandRepository
                .save(new BandEntity(band.getBandName(), band.getOrigin(), band.getActive(), artistEntities))
                .toBand();
    }
}
