package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;


import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlatformRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameCompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameCompanyEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("videoGameCompanyPersistance")
public class VideoGameCompanyPersistanceMongodb implements VideoGameCompanyPersistance {

    private final VideoGameCompanyRepository videoGameCompanyRepository;

    private final PlatformRepository platformRepository;

    @Autowired
    public VideoGameCompanyPersistanceMongodb(VideoGameCompanyRepository videoGameCompanyRepository, PlatformRepository platformRepository) {
        this.videoGameCompanyRepository = videoGameCompanyRepository;
        this.platformRepository = platformRepository;
    }

    @Override
    public Stream<VideoGameCompany> readAll() {
        return this.videoGameCompanyRepository.findAll().stream()
                .map(VideoGameCompanyEntity::toVideoGameCompany);
    }

    @Override
    public VideoGameCompany readByName(String name) {
        return null;
    }

    @Override
    public VideoGameCompany update(VideoGameCompany videoGameCompany) {
        return null;
    }


}