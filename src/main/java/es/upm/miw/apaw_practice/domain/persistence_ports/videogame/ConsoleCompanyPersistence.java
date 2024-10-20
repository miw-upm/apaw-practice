package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ConsoleCompanyPersistence {
    Stream<ConsoleCompany> readAll();
    ConsoleCompany readById(String id);
    ConsoleCompany updateConsoleCompany(ConsoleCompany consoleCompany);
    ConsoleCompany create(ConsoleCompany consoleCompany);
    boolean existsConsoleCompany(String consoleInformation);
    void delete(String consoleInformation);
}
