package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.BaseSportsAcademyTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class LegalGuardianRepositoryIT extends BaseSportsAcademyTests {

    @Autowired
    private LegalGuardianRepository legalGuardianRepository;

    @Test
    void testFindByUserDtoId(){
        assertTrue(this.legalGuardianRepository.findByUserDtoId(legalGuardians[0].getUserDtoId()).isPresent());
        var legalGuardian = this.legalGuardianRepository.findByUserDtoId(legalGuardians[0].getUserDtoId()).get();
        assertThat(legalGuardian).isNotNull();
        assertThat(legalGuardian.getUserDtoId()).isEqualTo(legalGuardians[0].getUserDtoId());
        assertThat(legalGuardian.getRelationShip()).isEqualTo(RelationShip.AUNT.getValue());
        assertThat(legalGuardian.getSecondMobile()).isEqualTo("34711036822");
    }

    @Test
    void testFindBySecondMobile(){
        var legalGuardians = this.legalGuardianRepository.findBySecondMobile(this.legalGuardians[0].getSecondMobile()).toList();
        assertThat(legalGuardians).hasSize(1);
        assertThat(legalGuardians.getFirst().getUserDtoId()).isEqualTo(this.legalGuardians[0].getUserDtoId());
        assertThat(legalGuardians.getFirst().getRelationShip()).isEqualTo(RelationShip.AUNT.getValue());
        assertThat(legalGuardians.getFirst().getSecondMobile()).isEqualTo("34711036822");
    }

    @Test
    void testFindByRelationShip() {
        var legalGuardians = this.legalGuardianRepository.findByRelationShip(RelationShip.FATHER.getValue()).toList();
        assertThat(legalGuardians).hasSize(1);
        assertThat(legalGuardians.getFirst().getUserDtoId()).isEqualTo(this.legalGuardians[2].getUserDtoId());
        assertThat(legalGuardians.getFirst().getRelationShip()).isEqualTo(RelationShip.FATHER.getValue());
        assertThat(legalGuardians.getFirst().getSecondMobile()).isEqualTo("34712036844");
    }
}
