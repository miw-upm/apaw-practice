package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class LegalGuardianPersistenceMongodbIT {

    @Autowired
    private LegalGuardianPersistenceMongodb legalGuardianPersistence;

    @Test
    void testGetByIdNotFound() {
        assertThrows(NotFoundException.class, () -> this.legalGuardianPersistence.getById(UUID.randomUUID()));
    }

    @Test
    void testCreateAndGetById() {
        LegalGuardian legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .secondMobile("+34711036899")
                .relationShip(RelationShip.AUNT)
                .build();
        this.legalGuardianPersistence.create(legalGuardian);
        LegalGuardian legalGuardianBD = this.legalGuardianPersistence.getById(legalGuardian.getUser().getId());
        assertThat(legalGuardianBD.getUser().getId()).isEqualTo(legalGuardian.getUser().getId());
        assertThat(legalGuardianBD.getSecondMobile()).isEqualTo("+34711036899");
        assertThat(legalGuardianBD.getRelationShip()).isEqualTo(RelationShip.AUNT);
    }

    @Test
    void testCreateAndUpdate() {
        LegalGuardian legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .secondMobile("+34711036888")
                .relationShip(RelationShip.AUNT)
                .build();
        LegalGuardian legalGuardianBD = this.legalGuardianPersistence.create(legalGuardian);
        legalGuardianBD.setSecondMobile("+34711036877");
        this.legalGuardianPersistence.update(legalGuardian.getUser().getId(), legalGuardianBD);
        legalGuardianBD = this.legalGuardianPersistence.getById(legalGuardian.getUser().getId());
        assertThat(legalGuardianBD.getSecondMobile()).isEqualTo("+34711036877");
    }

    @Test
    void testGetAll() {
        int page = 1;
        int pageSize = 10;
        var legalGuardians = this.legalGuardianPersistence.getAll(page, pageSize).toList();
        assertFalse(legalGuardians.isEmpty());
    }

    @Test
    void testGetAllPageOutOfRange() {
        int page = 100;
        int pageSize = 10;
        var legalGuardians = this.legalGuardianPersistence.getAll(page, pageSize).toList();
        assertTrue(legalGuardians.isEmpty());
    }

    @Test
    void testGetAllPageNegative() {
        int page = -1;
        int pageSize = 10;
        var legalGuardians = this.legalGuardianPersistence.getAll(page, pageSize).toList();
        assertFalse(legalGuardians.isEmpty());
    }

    @Test
    void testUpdateNotFound() {
        LegalGuardian legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .secondMobile("+34711036888")
                .relationShip(RelationShip.AUNT)
                .build();
        assertThrows(NotFoundException.class, () -> this.legalGuardianPersistence.update(legalGuardian.getUser().getId(), legalGuardian));
    }
}
