package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.ScreenRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreenPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("screenPersistence")
public class ScreenPersistenceMongodb implements ScreenPersistence {

    private final ScreenRepository screenRepository;

    @Autowired
    public ScreenPersistenceMongodb(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    @Override
    public Screen readByNumber(Integer number) {
        return this.screenRepository
                .findByNumber(number)
                .orElseThrow(() -> new NotFoundException("Screen number:" + number))
                .toScreen();
    }

    @Override
    public Screen update(Screen screen, List<Spectator> spectatorList) {
        screen.setSpectators(spectatorList);
        return screen;
    }
}
