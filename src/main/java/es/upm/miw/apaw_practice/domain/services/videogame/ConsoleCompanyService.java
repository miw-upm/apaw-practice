package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
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
        ConsoleCompany consoleCompany1 = this.consoleCompanyPersistence.readById(consoleInformation);
        consoleCompany1.setActive(true);
        return this.consoleCompanyPersistence.updateConsoleCompany(consoleCompany1);
    }

    public void updateAllCompanyActive(Stream<ConsoleCompanyActivedUpdating> consoleCompanyActivedUpdatingList) {
        consoleCompanyActivedUpdatingList.forEach(consoleCompanyNewStatus -> {updateActiveCompany(consoleCompanyNewStatus.getCompanyInformation());});
    }

    public ConsoleCompany create(ConsoleCompany consoleCompany) {
        this.assertCompanyInformationNotExist(consoleCompany.getCompanyInformation());
        return this.consoleCompanyPersistence.create(consoleCompany);
    }

    public void assertCompanyInformationNotExist(String consoleInformation) {
        if(this.consoleCompanyPersistence.existsConsoleCompany(consoleInformation)) {
            throw new ConflictException("ConsoleInformation exists: " + consoleInformation);
        }
    }
}