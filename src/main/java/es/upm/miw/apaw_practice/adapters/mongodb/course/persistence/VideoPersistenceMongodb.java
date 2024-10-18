package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.domain.models.course.Video;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.VideoPersistence;
import org.springframework.stereotype.Repository;

@Repository("videoPersistence")
public class VideoPersistenceMongodb implements VideoPersistence {

    @Override
    public Video update(String name, Video video) {
        return null;
    }
}
