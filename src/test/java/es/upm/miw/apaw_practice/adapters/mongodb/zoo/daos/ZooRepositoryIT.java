package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class ZooRepositoryIT {

    @Autowired
    private ZooRepository zooRepository;

    @Test
    void testFindAll() {
        Assertions.assertNotEquals(0, this.zooRepository.findAll().size());
        ZooAddress address = new ZooAddress("Portobello Road", 301, "GB 11K");
        Assertions.assertNotEquals(address, this.zooRepository.findAll().get(0).getAddress());
        Assertions.assertEquals(address, this.zooRepository.findAll().get(1).getAddress());
    }
}
