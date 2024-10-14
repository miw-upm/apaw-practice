package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ConsoleCompanyPersistence {
    Stream<ConsoleCompany> readAll();
    ConsoleCompany readByCompanyInformation(String companyInformation);
    ConsoleCompany updateConsoleCompany(ConsoleCompany consoleCompany);
}
