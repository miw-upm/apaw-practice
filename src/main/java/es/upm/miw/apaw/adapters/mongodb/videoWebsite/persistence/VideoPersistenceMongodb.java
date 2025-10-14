package es.upm.miw.apaw.adapters.mongodb.videoWebsite.persistence;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.VideoRepository;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.VideoEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
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

    @Override
    public Stream<Video> findByTitle(String title) {
        return this.videoRepository.findByTitle(title).stream()
                .map(VideoEntity::toVideo);
    }

    @Override
    public Video update(UUID id, Video newVideoData) {
        VideoEntity entity = this.videoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Video not found: " + id));

        entity.setTitle(newVideoData.getTitle());
        entity.setDescription(newVideoData.getDescription());
        entity.setVideoStatus(newVideoData.getVideoStatus());

        return this.videoRepository.save(entity).toVideo();
    }

    @Override
    public Video findById(UUID id) {
        VideoEntity entity = this.videoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Video not found: " + id));

        return entity.toVideo();
    }

    @Override
    public Video save(Video video) {
        VideoEntity entity = new VideoEntity(video);
        VideoEntity saved = this.videoRepository.save(entity);
        return saved.toVideo();
    }



}
