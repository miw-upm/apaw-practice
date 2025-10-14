package es.upm.miw.apaw.domain.persistenceports.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;
@Repository
public interface VideoPersistence {

    Stream<Video> findByTitle(String title);
    Video update(UUID id, Video video);
    Video findById(UUID id);
    Video save(Video video);

}
