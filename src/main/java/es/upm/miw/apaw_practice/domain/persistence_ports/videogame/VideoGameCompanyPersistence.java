package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface VideoGameCompanyPersistence {

    Stream<VideoGameCompany> readAll();

    VideoGameCompany readByName(String name);

    VideoGameCompany update(VideoGameCompany videoGameCompany);
}
