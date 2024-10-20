package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.ConsoleCompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleCompanyrEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.ConsoleCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.ConsoleCompanyPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Repository("consoleCompanyPersistence")
public class ConsoleCompanyPersistenceMongodb implements ConsoleCompanyPersistence {
    private final ConsoleCompanyRepository consoleCompanyRepository;

    @Autowired
    public ConsoleCompanyPersistenceMongodb(ConsoleCompanyRepository consoleCompanyRepository) {
        this.consoleCompanyRepository = consoleCompanyRepository;
    }

    @Override
    public Stream<ConsoleCompany> readAll() {
        return this.consoleCompanyRepository.findAll().stream()
                .map(ConsoleCompanyrEntity::toConsoleCompany);
    }

    @Override
    public ConsoleCompany readById(String id) {
        return this.consoleCompanyRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("ConsoleCompany with " + id + " not found"))
                .toConsoleCompany();
    }

    @Override
    public ConsoleCompany updateConsoleCompany(ConsoleCompany consoleCompany) {
        ConsoleCompanyrEntity consoleCompanyrEntity = this.consoleCompanyRepository
                .findById(consoleCompany.getCompanyInformation())
                .orElseThrow(() -> new NotFoundException("ConsoleCompany with " + consoleCompany + " not found"));
        BeanUtils.copyProperties(consoleCompany, consoleCompanyrEntity, "companyInformation");
        return this.consoleCompanyRepository.save(consoleCompanyrEntity)
                .toConsoleCompany();
    }

    @Override
    public ConsoleCompany create(ConsoleCompany consoleCompany) {
        return this.consoleCompanyRepository
                .save(new ConsoleCompanyrEntity("Sony","www.Sony.com",2000,false,LocalDate.of(2022,12,31),List.of()))
                .toConsoleCompany();
    }

    @Override
    public boolean existsConsoleCompany(String consoleCompany) {
        return this.consoleCompanyRepository.findById(consoleCompany).isPresent();
    }

    @Override
    public void delete(String companyInformation){
        this.consoleCompanyRepository.deleteByCompanyInformation(companyInformation);
    }

    @Override
    public ConsoleCompany update(String companyInformation, ConsoleCompany consoleCompany) {
        ConsoleCompanyrEntity consoleCompanyrEntity = consoleCompanyRepository
                .findByCompanyInformation(companyInformation)
                .orElseThrow(() -> new NotFoundException("ConsoleInformation: " + consoleCompany + " not found"));
        consoleCompanyrEntity.fromConsoleCompany(consoleCompany);
        return this.consoleCompanyRepository
                .save(consoleCompanyrEntity)
                .toConsoleCompany();
    }
}