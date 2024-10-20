package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.ConsoleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleEntity;
import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.ConsolePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Repository("consolePersistence")
public class ConsolePersistenceMongodb implements ConsolePersistence {
    private final ConsoleRepository consoleRepository;

    @Autowired
    public ConsolePersistenceMongodb(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    @Override
    public Stream<Console> findByConsoleReference(String consoleReference){
        return this.consoleRepository.findAll().stream()
                .filter(console -> consoleReference.equals(console.getConsoleReference()))
                .map(ConsoleEntity::toConsole);
    }

    @Override
    public void delete(String consoleReference) {
        this.consoleRepository.deleteByConsoleReference(consoleReference);
    }

    @Override
    public Console create(Console console) {
        return this.consoleRepository
                .save(new ConsoleEntity("Rabbit", 9875456464646L, LocalDate.of(1985,9,6), List.of(),true))
                .toConsole();
    }

    @Override
    public boolean existsConsole(String consoleReference) {
        return this.consoleRepository.findByConsoleReference(consoleReference).isPresent();
    }
}