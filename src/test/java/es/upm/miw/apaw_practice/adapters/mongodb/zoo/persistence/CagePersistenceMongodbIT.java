package es.upm.miw.apaw_practice.adapters.mongodb.zoo.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.CageRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.ZooRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.zoo.CageFumigation;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.CagePersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@TestConfig
public class CagePersistenceMongodbIT {

    @Autowired
    private ZooRepository zooRepository;
    @Autowired
    private CageRepository cageRepository;
    @Autowired
    private CagePersistence cagePersistence;

    @Test
    void testUpdateNextFumigation() {
        ZooEntity zoo = this.zooRepository.findById("id1")
                .orElseThrow(() -> new NotFoundException("test error"));

        Long countOfAll = this.cageRepository.findByZoo(zoo).count();
        Assertions.assertNotEquals(0, countOfAll);
        Assertions.assertEquals(countOfAll,
                this.cageRepository.findByZoo(zoo)
                        .filter(cage -> cage.getNextFumigation().equals(LocalDate.now()))
                        .count());

        LocalDate oldDate = LocalDate.now();
        LocalDate newDate = LocalDate.of(1997, 10, 21);
        CageFumigation cageFumigation = new CageFumigation(oldDate, newDate);
        this.cagePersistence.updateNextFumigation(zoo, cageFumigation);

        countOfAll = this.cageRepository.findByZoo(zoo).count();
        Assertions.assertNotEquals(0, countOfAll);
        Assertions.assertEquals(0,
                this.cageRepository.findByZoo(zoo)
                        .filter(cage -> cage.getNextFumigation().equals(oldDate))
                        .count());
        Assertions.assertEquals(countOfAll,
                this.cageRepository.findByZoo(zoo)
                        .filter(cage -> cage.getNextFumigation().equals(newDate))
                        .count());

        cageFumigation = new CageFumigation(newDate, oldDate);
        this.cagePersistence.updateNextFumigation(zoo, cageFumigation);

        countOfAll = this.cageRepository.findByZoo(zoo).count();
        Assertions.assertNotEquals(0, countOfAll);
        Assertions.assertEquals(countOfAll,
                this.cageRepository.findByZoo(zoo)
                        .filter(cage -> cage.getNextFumigation().equals(oldDate))
                        .count());
        Assertions.assertEquals(0,
                this.cageRepository.findByZoo(zoo)
                        .filter(cage -> cage.getNextFumigation().equals(newDate))
                        .count());
    }
}
