package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ConsolePersistence {
    Stream<Console> findByConsoleReference(String consoleReference);
    void delete(String consoleReference);
    boolean existsConsole(String consoleReference);
    Console create(Console console);
    Console update(String consoleReference, Console console);
}