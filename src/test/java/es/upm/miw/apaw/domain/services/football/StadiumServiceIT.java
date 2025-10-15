package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.football.Stadium;
import es.upm.miw.apaw.domain.persistenceports.football.StadiumPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class StadiumServiceIT {

    @Autowired
    private StadiumService stadiumService;

    @MockitoBean
    private StadiumPersistence stadiumPersistence;

    @Test
    void testCreate_ok() {
        Stadium stadium = Stadium.builder()
                .stadiumId(1L)
                .officialName("Old Trafford")
                .capacity(75000)
                .roof(true)
                .build();

        BDDMockito.given(this.stadiumPersistence.existsByOfficialName("Old Trafford"))
                .willReturn(false);
        BDDMockito.given(this.stadiumPersistence.save(stadium))
                .willReturn(stadium);

        Stadium created = this.stadiumService.create(stadium);

        assertThat(created).isNotNull();
        assertThat(created.getOfficialName()).isEqualTo("Old Trafford");
        assertThat(created.getCapacity()).isEqualTo(75000);
        assertThat(created.getRoof()).isTrue();
    }

    @Test
    void testCreate_conflictException() {
        Stadium stadium = Stadium.builder()
                .stadiumId(2L)
                .officialName("Duplicate Stadium")
                .capacity(60000)
                .roof(false)
                .build();

        BDDMockito.given(this.stadiumPersistence.existsByOfficialName("Duplicate Stadium"))
                .willReturn(true);

        assertThrows(ConflictException.class,
                () -> this.stadiumService.create(stadium));
    }

    @Test
    void testCreate_badRequestException() {
        Stadium stadium = Stadium.builder()
                .stadiumId(3L)
                .officialName("Invalid Capacity Stadium")
                .capacity(0)
                .roof(true)
                .build();

        assertThrows(BadRequestException.class,
                () -> this.stadiumService.create(stadium));
    }
}
