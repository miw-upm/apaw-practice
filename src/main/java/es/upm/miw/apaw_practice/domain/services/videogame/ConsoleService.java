package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.ConsolePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ConsoleService {
    private final ConsolePersistence consolePersistence;

    @Autowired
    public ConsoleService(ConsolePersistence consolePersistence) {
        this.consolePersistence = consolePersistence;
    }

    public Stream<Console> findByConsoleReference(String consoleReference) {
        return this.consolePersistence.findByConsoleReference(consoleReference);
    }
}
