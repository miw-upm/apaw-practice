package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.LegalGuardianRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.BaseSportsAcademyTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class LegalGuardianPersistenceMongodbIT extends BaseSportsAcademyTests {

    @Autowired
    private LegalGuardianPersistenceMongodb legalGuardianPersistence;

    @Test
    void testGetByIdNotFound() {
        var id = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> this.legalGuardianPersistence.getById(id));
    }

    @Test
    void testCreateAndGetById(@Autowired LegalGuardianRepository legalGuardianRepository) {
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
        legalGuardianRepository.deleteById(legalGuardian.getUser().getId());
    }

    @Test
    void testCreateAndUpdate(@Autowired LegalGuardianRepository legalGuardianRepository) {
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
        legalGuardianRepository.deleteById(legalGuardian.getUser().getId());
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
        var id = UUID.randomUUID();
        LegalGuardian legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(id).build())
                .secondMobile("+34711036888")
                .relationShip(RelationShip.AUNT)
                .build();
        assertThrows(NotFoundException.class, () -> this.legalGuardianPersistence.update(id, legalGuardian));
    }

    @Test
    void testGetBySecondMobile(){
        String secondMobile = legalGuardians[0].getSecondMobile();
        var legalGuardians = this.legalGuardianPersistence.getBySecondMobile(secondMobile).toList();
        assertFalse(legalGuardians.isEmpty());
        assertThat(legalGuardians.getFirst().getSecondMobile()).isEqualTo(secondMobile);
        assertThat(legalGuardians.getFirst().getRelationShip()).isEqualTo(RelationShip.AUNT);
        assertThat(legalGuardians.getFirst().getUser().getId()).isEqualTo(this.legalGuardians[0].getUserDtoId());
    }

    @Test
    void testGetByRelationShip() {
        var legalGuardians = this.legalGuardianPersistence.getByRelationShip(RelationShip.FATHER).toList();
        assertFalse(legalGuardians.isEmpty());
        assertThat(legalGuardians.getFirst().getSecondMobile()).isEqualTo("34712036844");
        assertThat(legalGuardians.getFirst().getRelationShip()).isEqualTo(RelationShip.FATHER);
        assertThat(legalGuardians.getFirst().getUser().getId()).isEqualTo(this.legalGuardians[2].getUserDtoId());
    }
}
