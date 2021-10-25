package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlatformRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameCompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlatformEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameCompanyEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("videoGameCompanyPersistence")
public class VideoGameCompanyPersistenceMongodb implements VideoGameCompanyPersistence {

    private final VideoGameCompanyRepository videoGameCompanyRepository;
    private final PlatformRepository platformRepository;

    @Autowired
    public VideoGameCompanyPersistenceMongodb(VideoGameCompanyRepository videoGameCompanyRepository, PlatformRepository platformRepository) {
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
        return this.videoGameCompanyRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Company name:" + name))
                .toVideoGameCompany();
    }

    @Override
    public VideoGameCompany update(VideoGameCompany videoGameCompany) {
        VideoGameCompanyEntity videoGameCompanyEntity = this.videoGameCompanyRepository
                .findByName(videoGameCompany.getName())
                .orElseThrow(() -> new NotFoundException("Company name:" + videoGameCompany.getName()));

        List<PlatformEntity> platformEntities = videoGameCompany.getPlatforms().stream()
                .map(platform -> new PlatformEntity(
                        this.platformRepository
                                .findByConsoleName(platform.getConsoleName())
                                .orElseThrow(() -> new NotFoundException("Platform name: "
                                        + platform.getConsoleName())).toPlatform())
                ).collect(Collectors.toList());
        videoGameCompanyEntity.setPlatformEntities(platformEntities);
        return this.videoGameCompanyRepository.save(videoGameCompanyEntity).toVideoGameCompany();
    }
}