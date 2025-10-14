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
import java.util.stream.Stream;

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

    @Test
    void testGetBySecondMobile() {
        var secondMobile = "+5549988706208";
        LegalGuardian legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .relationShip(RelationShip.FATHER)
                .secondMobile(secondMobile)
                .build();
        when(legalGuardianPersistence.getBySecondMobile(secondMobile)).thenReturn(Stream.of(legalGuardian));
        var legalGuardianList = this.legalGuardianService.getBySecondMobile(secondMobile).toList();
        assertThat(legalGuardianList.size()).isEqualTo(1);
        assertThat(legalGuardianList.getFirst()).isEqualTo(legalGuardian);
    }

    @Test
    void testGetByRelationShip() {
        var relationShip = RelationShip.FATHER;
        LegalGuardian legalGuardian = LegalGuardian.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .relationShip(relationShip)
                .secondMobile("+5549988706208")
                .build();
        when(legalGuardianPersistence.getByRelationShip(relationShip)).thenReturn(Stream.of(legalGuardian));
        var legalGuardianList = this.legalGuardianService.getByRelationShip(relationShip).toList();
        assertThat(legalGuardianList.size()).isEqualTo(1);
        assertThat(legalGuardianList.getFirst()).isEqualTo(legalGuardian);
    }
}
