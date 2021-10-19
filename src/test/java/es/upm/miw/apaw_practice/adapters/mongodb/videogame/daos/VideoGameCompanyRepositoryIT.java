package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class VideoGameCompanyRepositoryIT {

    @Autowired
    private VideoGameCompanyRepository videoGameCompanyRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.videoGameCompanyRepository.findAll().stream()
                .anyMatch(company ->
                        "nintendo".equals(company.getName()) &&
                                company.getId() != null &&
                                company.getFormationDate() != null &&
                                company.getFormationDate().equals(LocalDate.of(1889, 9, 23)) &&
                                2 == company.getPlatforms().size() &&
                                "8gb".equals(company.getPlatforms().get(1).getMemory()) &&
                                "oled".equals(company.getPlatforms().get(0).getModel())
                ));
    }
}
