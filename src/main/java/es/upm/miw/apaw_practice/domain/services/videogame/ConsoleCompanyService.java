package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.ConsoleCompanyRepository;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompanyActivedUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.ConsoleCompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ConsoleCompanyService {

    private final ConsoleCompanyPersistence consoleCompanyPersistence;

    @Autowired
    public ConsoleCompanyService(ConsoleCompanyPersistence consoleCompanyPersistence) {
        this.consoleCompanyPersistence = consoleCompanyPersistence;
    }

    public ConsoleCompany updateActiveCompany(String consoleInformation) {
        ConsoleCompany consoleCompany1 = this.consoleCompanyPersistence.readByCompanyInformation(consoleInformation);
        consoleCompany1.setActive(true);
        return this.consoleCompanyPersistence.updateConsoleCompany(consoleCompany1);
    }

    public void updateAllCompanyActive(Stream<ConsoleCompanyActivedUpdating> consoleCompanyActivedUpdatingStream) {
        consoleCompanyActivedUpdatingStream.forEach(consoleCompanyNewStatus -> {updateActiveCompany(consoleCompanyNewStatus.getCompanyInformation());});
    }
}
