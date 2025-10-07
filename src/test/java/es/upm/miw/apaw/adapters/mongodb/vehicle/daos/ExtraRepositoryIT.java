package es.upm.miw.apaw.adapters.mongodb.vehicle.daos;

import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.ExtraEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ExtraRepositoryIT {

    @Autowired
    private ExtraRepository extraRepository;

    @Test
    void testFindAll() {
        assertThat(this.extraRepository.findAll())
                .isNotEmpty()
                .anySatisfy(extra -> {
                    assertThat(extra.getId()).isInstanceOf(UUID.class);
                    assertThat(extra.getCategory()).isNotBlank();
                    assertThat(extra.getDescription()).isNotBlank();
                    assertThat(extra.getPrice()).isNotNull();
                });
    }

    @Test
    void testFindByCategoryAndDescription() {
        String category = "Safety";
        String description = "Automatic Emergency Braking (AEB)";

        assertThat(this.extraRepository.findByCategoryAndDescription(category, description))
                .isPresent()
                .get()
                .extracting(ExtraEntity::getCategory)
                .isEqualTo(category);
    }
}
