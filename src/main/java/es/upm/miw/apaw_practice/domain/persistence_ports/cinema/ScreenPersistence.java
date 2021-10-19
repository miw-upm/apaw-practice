package es.upm.miw.apaw_practice.domain.persistence_ports.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenPersistence {

    Screen readByNumber(Integer number);

    Screen update(Screen screen, List<Spectator> spectatorList);
}
