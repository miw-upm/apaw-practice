package es.upm.miw.apaw_practice.domain.services.course;


import es.upm.miw.apaw_practice.domain.models.course.Video;
import es.upm.miw.apaw_practice.domain.persistence_ports.course.VideoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    private final VideoPersistence videoPersistence;

    @Autowired
    public VideoService(VideoPersistence videoPersistence) {
        this.videoPersistence = videoPersistence;
    }

    public Video update(String name, Video video) {
        return null;
    }

}
