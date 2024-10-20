package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
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

    public void delete(String consoleReference) {
        this.consolePersistence.delete(consoleReference);
    }

    public Console create(Console console) {
        this.assertConsoleNotExist(console.getConsoleReference());
        return this.consolePersistence.create(console);
    }

    public void assertConsoleNotExist(String consoleReference) {
        if(this.consolePersistence.existsConsole(consoleReference)){
            throw new ConflictException("ConsoleReference exists: " + consoleReference);
        }
    }

    public Console update(String consoleReference, Console console) {
        return this.consolePersistence.update(consoleReference, console);
    }
}