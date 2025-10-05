package es.upm.miw.apaw.domain.services.sports.academy.legal.guardian;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.LegalGuardian;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.ILegalGuardianPersistence;
import es.upm.miw.apaw.domain.services.sports.academy.LegalGuardianService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class LegalGuardianServiceTest {
    @Autowired
    private LegalGuardianService legalGuardianService;
    @MockitoBean
    private ILegalGuardianPersistence legalGuardianPersistence;

    @Test
    void testUpdate() {
        var id = UUID.randomUUID();
        LegalGuardian legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(id).build())
                .relationShip(RelationShip.FATHER)
                .secondMobile("+5549988706208")
                .build();
        when(legalGuardianPersistence.getById(id)).thenReturn(legalGuardian);
        when(legalGuardianPersistence.update(id, legalGuardian)).thenReturn(legalGuardian);
        LegalGuardian result = legalGuardianService.update(id, legalGuardian);
        assertThat(result.getUser().getId()).isEqualTo(id);
        assertThat(result.getRelationShip()).isEqualTo(RelationShip.FATHER);
        assertThat(result.getSecondMobile()).isEqualTo("+5549988706208");
    }
}
