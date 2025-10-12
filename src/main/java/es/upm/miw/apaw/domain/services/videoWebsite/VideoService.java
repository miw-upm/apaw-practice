package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.VideoPersistence;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class VideoService {

    private final VideoPersistence videoPersistence;

    public VideoService(VideoPersistence videoPersistence) {
        this.videoPersistence = videoPersistence;
    }

    public Stream<Video> findByTitle(String title) {
        return videoPersistence.findByTitle(title);
    }


}
