package es.upm.miw.apaw.domain.services.sports.academy.legal.guardian;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.domain.services.sports.academy.LegalGuardianService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class LegalGuardianServiceIT {

    @Autowired
    private LegalGuardianService legalGuardianService;

    @Test
    void update() {
        var id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");
        var legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(id).build())
                .secondMobile("+5549988706208")
                .relationShip(RelationShip.OTHER)
                .build();
        var legalGuardianUpdated = this.legalGuardianService.update(id, legalGuardian);
        assertThat(legalGuardianUpdated.getUser().getId()).isEqualTo(id);
        assertThat(legalGuardianUpdated.getSecondMobile()).isEqualTo("+5549988706208");
        assertThat(legalGuardianUpdated.getRelationShip()).isEqualTo(RelationShip.OTHER);
    }
}
