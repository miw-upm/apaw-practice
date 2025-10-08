package es.upm.miw.apaw.domain.services.fighters;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.fighters.Coach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class CoachServiceIT {

    @Autowired
    private CoachService coachService;

    @Test
    void testReadByFullName() {
        Coach coach = this.coachService.readByFullName("John Smith");
        assertThat(coach.getFullName()).isEqualTo("John Smith");
        assertThat(coach.getAcademy()).isEqualTo("Nova Gym");
        assertThat(coach.getExperienceYears()).isEqualTo(12);
    }

    @Test
    void testReadByFullName_notFound() {
        assertThrows(NotFoundException.class, () -> this.coachService.readByFullName("no existe"));
    }
}
