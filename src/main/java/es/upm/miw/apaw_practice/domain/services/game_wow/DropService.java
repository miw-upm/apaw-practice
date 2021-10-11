package es.upm.miw.apaw_practice.domain.services.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.BossPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DropService {

    private final BossPersistence bossPersistence;

    @Autowired
    public DropService(BossPersistence bossPersistence) {
        this.bossPersistence = bossPersistence;
    }

    public Stream<Boss> findByEffort(String effort) {

        Parecido, pero el filtrado de effort, ya esta implementado en PersistenceBoss, por eso le pasamos el parámetro
        return bossPersistence.readAll()
                .filter(boss -> boss.getEffort().equals(effort));
    }
}
