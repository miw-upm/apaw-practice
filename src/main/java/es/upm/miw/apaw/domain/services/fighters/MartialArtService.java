package es.upm.miw.apaw.domain.services.fighters;

import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import es.upm.miw.apaw.domain.persistenceports.fighters.MartialArtPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MartialArtService {
    private final MartialArtPersistence martialArtPersistence;

    @Autowired
    public MartialArtService(MartialArtPersistence martialArtPersistence){
        this.martialArtPersistence = martialArtPersistence;
    }

    public MartialArt update(String discipline, MartialArt body){
        MartialArt updatedMartialArt = this.martialArtPersistence.readByDiscipline(discipline);
        updatedMartialArt.setDescription(body.getDescription());
        updatedMartialArt.setOrigin(body.getOrigin());
        updatedMartialArt.setStriking(body.getStriking());
        updatedMartialArt.setGrappling(body.getGrappling());
        return this.martialArtPersistence.update(updatedMartialArt);
    }
}
