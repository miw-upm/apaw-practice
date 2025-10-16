package es.upm.miw.apaw.domain.services.sports.academy.sport.mobility;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.sports.academy.dtos.UpdateSportMobilityActivation;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.TargetAudience;
import es.upm.miw.apaw.BaseSportsAcademyTests;
import es.upm.miw.apaw.domain.services.sports.academy.SportModalityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class SportMobilityServiceIT extends BaseSportsAcademyTests {
    @Autowired
    private SportModalityService sportModalityService;

    @Test
    void testUpdateActivation(){
        UUID id = sportModalities[0].getId();
        var dto = UpdateSportMobilityActivation.builder().active(true).build();
        this.sportModalityService.updateActivation(id, dto);
        var sportModality = this.sportModalityService.getById(id);
        assertThat(sportModality.isActive()).isTrue();
    }

    @Test
    void testGetById(){
        UUID id = sportModalities[1].getId();
        var sportModality = this.sportModalityService.getById(id);
        assertThat(sportModality.getId()).isEqualTo(id);
        assertThat(sportModality.getTitle()).isEqualTo("Swimming");
        assertThat(sportModality.isActive()).isFalse();
        assertThat(sportModality.getLevel()).isEqualTo(Level.INTERMEDIATE);
        assertThat(sportModality.getTargetAudience()).isEqualTo(TargetAudience.TEENAGERS);
        assertThat(sportModality.getProfessor().getUser().getId())
                .isEqualTo(sportModalities[1].getProfessor().getUserDtoId());
    }

    @Test
    void testDelete(){
        UUID id = sportModalities[0].getId();
        this.sportModalityService.delete(id);
        assertThrows(NotFoundException.class, () -> this.sportModalityService.getById(id));
    }
}
