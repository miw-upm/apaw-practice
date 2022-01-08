package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class TrainingItemEntityRepositoryIT {
    @Autowired
    private TrainingItemRepository trainingItemRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.trainingItemRepository.findAll().stream()
                .anyMatch(item ->
                        "Social Psychology".equals(item.getName()) &&
                                "Psychology".equals(item.getKnowledgeField()) &&
                                item.getId() != null &&
                                "1468048B".equals(item.getLecturerEntity().getDni())
                ));
    }
}
