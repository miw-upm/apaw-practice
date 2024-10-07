package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos.ThemeParkRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ThemeParkRepositoryIT {

    @Autowired
    private ThemeParkRepository themeParkRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.themeParkRepository.findAll().stream()
                .anyMatch(themePark ->
                        themePark.getOpened() &&
                                0 == new BigDecimal("60.00").compareTo(themePark.getPrice()) &&
                                themePark.getId() != null &&
                                themePark.getCreationDate() != null &&
                                themePark.getCreationDate().isBefore(LocalDateTime.now()) &&
                                2 == themePark.getRideEntities().size() &&
                                "Tarántula".equals(themePark.getRideEntities().get(0).getName()) &&
                                "Halloween".equals(themePark.getRideEntities().get(0).getTheme()) &&
                                themePark.getRideEntities().get(0).getUserEntities().stream()
                                .map(UserEntity::getIdMembership)
                                .toList()
                                .containsAll(Arrays.asList("712", "14674")) &&
                                (!themePark.getRideEntities().get(0).getFavourite())
                    ));
    }

}
