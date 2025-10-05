package es.upm.miw.apaw.domain.services.sports.academy.sport.mobility;

import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.models.sports.academy.SportModality;
import es.upm.miw.apaw.domain.models.sports.academy.dtos.UpdateSportMobilityActivation;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.TargetAudience;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.ISportModalityPersistence;
import es.upm.miw.apaw.domain.services.sports.academy.SportModalityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class SportMobilityServiceTest {
    @Autowired
    private SportModalityService sportModalityService;
    @MockitoBean
    private ISportModalityPersistence sportModalityPersistence;

    @Test
    void testUpdateActivation(){
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0007");
        SportModality sportModality = SportModality.builder()
                .id(id)
                .title("Swimming")
                .level(Level.INTERMEDIATE)
                .targetAudience(TargetAudience.TEENAGERS)
                .professor(new Professor())
                .active(false)
                .build();
        when(sportModalityPersistence.getById(id)).thenReturn(sportModality);
        var dto = UpdateSportMobilityActivation.builder().active(true).build();
        this.sportModalityService.updateActivation(id, dto);
        sportModality = this.sportModalityService.getById(id);
        assertThat(sportModality.isActive()).isTrue();
    }

    @Test
    void testGetById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006");
        SportModality sportModality = SportModality.builder()
                .id(id)
                .title("Swimming")
                .level(Level.INTERMEDIATE)
                .targetAudience(TargetAudience.TEENAGERS)
                .professor(new Professor())
                .active(false)
                .build();
        when(sportModalityPersistence.getById(id)).thenReturn(sportModality);
        sportModality = this.sportModalityService.getById(id);
        assertThat(sportModality.getId()).isEqualTo(id);
        assertThat(sportModality.getTitle()).isEqualTo("Swimming");
        assertThat(sportModality.isActive()).isFalse();
        assertThat(sportModality.getLevel()).isEqualTo(Level.INTERMEDIATE);
        assertThat(sportModality.getTargetAudience()).isEqualTo(TargetAudience.TEENAGERS);
        assertThat(sportModality.getProfessor()).isInstanceOf(Professor.class);
    }
}
