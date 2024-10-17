package es.upm.miw.apaw_practice.domain.persistence_ports.course;

import es.upm.miw.apaw_practice.domain.models.course.Video;

public interface VideoPersistence {
    Video update(String name, Video video);
}
