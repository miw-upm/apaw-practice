package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;


import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlatformRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameCompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlatformEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameCompanyEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
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
        VideoGameCompanyEntity videoGameCompanyEntity = this.videoGameCompanyRepository
                .findById(videoGameCompany.getName())
                .orElseThrow(() -> new NotFoundException("Company name:" + videoGameCompany.getName()));
        List<PlatformEntity> platformEntities = videoGameCompany.getPlatforms().stream()
                .map(platform -> new PlatformEntity(
                        this.platformRepository
                                .findByConsoleName(platform.getConsoleName())
                                .orElseThrow(() -> new NotFoundException("Platform consoleName: " + platform.getConsoleName())).getConsoleName(),
                        platform.getModel(),
                        platform.getMemory())
                ).collect(Collectors.toList());
        videoGameCompanyEntity.setPlatformEntities(platformEntities);
        return this.videoGameCompanyRepository.save(videoGameCompanyEntity).toVideoGameCompany();
    }

}