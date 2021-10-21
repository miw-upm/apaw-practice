package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.LearnerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class LearnerRepositoryIT {
    @Autowired
    private LearnerRepository learnerRepository;

    @Test
    void testCreateAndRead(){
        List<LearnerEntity> list = this.learnerRepository.findAll();
        assertTrue(list.stream()
                .anyMatch(myLearner -> "wang".equals(myLearner.getName())));
    }
}
