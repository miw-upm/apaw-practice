package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreenPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {
    private final ScreenPersistence screenPersistence;

    @Autowired
    public ScreenService(ScreenPersistence screenPersistence) {
        this.screenPersistence = screenPersistence;
    }

    public Screen updateSpectators(Integer number, List<Spectator> spectatorList) {
        Screen screen = this.screenPersistence.readByNumber(number);
        return this.screenPersistence.update(screen, spectatorList);

    }

    public List<String> getActorsNameByScreenNumber(Integer number) {
        return this.screenPersistence.getActorsNameByScreenNumber(number);
    }
}
