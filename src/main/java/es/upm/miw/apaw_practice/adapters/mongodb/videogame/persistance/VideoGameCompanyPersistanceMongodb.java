package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameCompanyRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("videoGameCompanyPersistance")
public class VideoGameCompanyPersistanceMongodb implements VideoGameCompanyPersistance {

    private final VideoGameCompanyRepository videoGameCompanyRepository;

    @Autowired
    public VideoGameCompanyPersistanceMongodb(VideoGameCompanyRepository videoGameCompanyRepository) {
        this.videoGameCompanyRepository = videoGameCompanyRepository;
    }
}
