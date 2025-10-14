package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.VideoStatusUpdating;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.VideoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class VideoService {

    private final VideoPersistence videoPersistence;

    @Autowired
    public VideoService(VideoPersistence videoPersistence) {
        this.videoPersistence = videoPersistence;
    }

    public Stream<Video> findByTitle(String title) {
        return videoPersistence.findByTitle(title);
    }

    public Video update(UUID id, Video newVideoData) {
        Video existing = this.videoPersistence.findById(id);
        existing.setTitle(newVideoData.getTitle());
        existing.setDescription(newVideoData.getDescription());
        existing.setVideoStatus(newVideoData.getVideoStatus());
        return this.videoPersistence.save(existing);
    }

    public Video save(Video newVideoData) {
        return this.videoPersistence.save(newVideoData);
    }

    public void updateVideoStatus(Stream<VideoStatusUpdating> videoStatusUpdatingList) {
        videoStatusUpdatingList.map(statusUpdate -> {
            Video video = this.videoPersistence.findById(statusUpdate.getId());
            video.setVideoStatus(statusUpdate.getStatus());
            return video;
        })
                .forEach(video -> this.videoPersistence.update(video.getId(), video));
    }

    public Video findById(UUID id) {
        return this.videoPersistence.findById(id);
    }
}
