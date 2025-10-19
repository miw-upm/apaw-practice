package es.upm.miw.apaw.domain.services.sports.academy.legal.guardian;

import es.upm.miw.apaw.domain.models.sports.academy.dtos.UpdateLegalGuardian;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.BaseSportsAcademyTests;
import es.upm.miw.apaw.domain.services.sports.academy.LegalGuardianService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class LegalGuardianServiceIT extends BaseSportsAcademyTests {

    @Autowired
    private LegalGuardianService legalGuardianService;

    @Test
    void testUpdate() {
        var id = legalGuardians[0].getUserDtoId();
        var legalGuardian = UpdateLegalGuardian.builder()
                .secondMobile("+5549988706208")
                .relationShip(RelationShip.OTHER)
                .build();
        var legalGuardianUpdated = this.legalGuardianService.update(id, legalGuardian);
        assertThat(legalGuardianUpdated.getUser().getId()).isEqualTo(id);
        assertThat(legalGuardianUpdated.getSecondMobile()).isEqualTo("+5549988706208");
        assertThat(legalGuardianUpdated.getRelationShip()).isEqualTo(RelationShip.OTHER);
    }

    @Test
    void testGetBySecondMobile(){
        var legalGuardianList = this.legalGuardianService.getBySecondMobile(athletes[0].getLegalGuardians().getFirst().getSecondMobile()).toList();
        assertThat(legalGuardianList).hasSize(1);
        assertThat(legalGuardianList.getFirst()).isEqualTo(athletes[0].getLegalGuardians().getFirst().toLegalGuardian());
    }

    @Test
    void testGetByRelationShip(){
        var legalGuardianList = this.legalGuardianService.getByRelationShip(RelationShip.FATHER).toList();
        assertThat(legalGuardianList).allMatch(legalGuardian -> legalGuardian.getRelationShip().equals(RelationShip.FATHER));
        assertThat(legalGuardianList.getFirst()).isEqualTo(athletes[2].getLegalGuardians().getFirst().toLegalGuardian());
    }
}
