package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.EmarketerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.EmarketerEntity;
import es.upm.miw.apaw_practice.domain.models.emarketer.Emarketer;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.EmarketerPersistence;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("emarketerPersistence")
public class EmarketerPersistenceMongodb implements EmarketerPersistence {

    private final EmarketerRepository emarkterRepository;

    @Autowired
    public EmarketerPersistenceMongodb(EmarketerRepository emarkterRepository) {
        this.emarkterRepository = emarkterRepository;
    }

    @Override
    public void delete(String name) {
        this.emarkterRepository.deleteByName(name);
    }

    @Override
    public Stream<Emarketer> readAll() {
        return this.emarkterRepository.findAll().stream()
                .map(EmarketerEntity::toEmarketer);
    }

    @Override
    public Stream<Emarketer> readByCups(String cups) {
        return this.readAll()
                .filter(emarketer -> emarketer.getCups().stream()
                        .anyMatch(cup -> cup.getCups().equals(cups)));
    }

    @Override
    public Stream<Emarketer> readByPlanDescription(String description) {
        return this.readAll()
                .filter(emarketer -> emarketer.getPlans().stream()
                        .anyMatch(plan -> plan.getDescription().equals(description)));
    }

}
