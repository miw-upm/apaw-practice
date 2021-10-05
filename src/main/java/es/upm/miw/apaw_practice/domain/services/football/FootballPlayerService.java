package es.upm.miw.apaw_practice.domain.services.football;

import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.FootballPlayerPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ArticlePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class FootballPlayerService {
    private final FootballPlayerPersistence footballPlayerPersistence;

    @Autowired
    public FootballPlayerService(FootballPlayerPersistence footballPlayerPersistence) {
        this.footballPlayerPersistence = footballPlayerPersistence;
    }

    public Stream<FootballPlayer> readAll() {
        return footballPlayerPersistence.readAll();
    }
}
