package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGamerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
public class ConsoleCompanyRepositoryIT {

    @Autowired
    private ConsoleCompanyRepository consoleCompanyRepository;

    @Test
    void testCreateAndRead(){
        assertFalse(this.consoleCompanyRepository.findAll().stream()
                .anyMatch(consoleCompany ->
                        consoleCompany.getActive() &&
                                consoleCompany.getCompanyInformation() != null &&
                                consoleCompany.getFoundationDate() != null &&
                        consoleCompany.getFoundationDate().isBefore(LocalDate.now()) &&
                        2 == consoleCompany.getConsoleEntities().size() &&
                        "PlayStation".equals(consoleCompany.getConsoleEntities().get(0).getConsoleReference()) &&
                                "GameCube".equals(consoleCompany.getConsoleEntities().get(0).getConsoleReference()) &&
                        consoleCompany.getConsoleEntities().get(0).getVideoGameEntities().stream()
                                .map(VideoGamerEntity::getVideoGameAlias)
                                .toList()
                                .containsAll(Arrays.asList("Fable", "Dante Inferno")) &&
                                (!consoleCompany.getConsoleEntities().get(0).getPortable())));
    }
}