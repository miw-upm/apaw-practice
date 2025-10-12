package es.upm.miw.apaw.adapters.mongodb.videoWebsite.persistence;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.VideoRepository;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.VideoEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.VideoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("videoPersistence")
public class VideoPersistenceMongodb implements VideoPersistence{

    private final VideoRepository videoRepository;

    @Autowired
    public VideoPersistenceMongodb(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }
//
//    @Override
//    public Video readById(UUID id) {
//        return this.videoRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException(" ID name: " + id))
//                .toVideo();
//    }

    public Stream<Video> findByTitle(String title) {
        return this.videoRepository.findByTitle(title).stream()
                .map(VideoEntity::toVideo);
    }

}
