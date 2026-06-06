package es.upm.miw.apaw.domain.services.theater;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.models.theater.TheaterVenue;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterVenuePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class TheaterVenueServiceTest {

    @Autowired
    private TheaterVenueService theaterVenueService;

    @MockitoBean
    private TheaterVenuePersistence theaterVenuePersistence;

    @Test
    void testRead() {
        TheaterVenue venue = TheaterVenue.builder()
                .venueCode("TVEN01")
                .venueName("Gran Teatro")
                .venueCity("Madrid")
                .venueOpen(true)
                .venueCreatedAt(LocalDateTime.of(2020, 1, 10, 0, 0))
                .venueHalls(List.of(
                        TheaterHall.builder().hallCode("HAL01").hallName("Main Stage").hallCapacity(500).hallAccessible(true).build()
                ))
                .venueManager(UserDto.builder().id(UUID.randomUUID()).build())
                .build();
        BDDMockito.given(this.theaterVenuePersistence.read("TVEN01")).willReturn(venue);

        TheaterVenue result = this.theaterVenueService.read("TVEN01");

        assertThat(result.getVenueCode()).isEqualTo("TVEN01");
        assertThat(result.getVenueName()).isEqualTo("Gran Teatro");
        assertThat(result.getVenueCity()).isEqualTo("Madrid");
        assertThat(result.getVenueOpen()).isTrue();
        assertThat(result.getVenueHalls()).hasSize(1);
        assertThat(result.getVenueHalls().getFirst().getHallCode()).isEqualTo("HAL01");
        assertThat(result.getVenueManager()).isNotNull();
        BDDMockito.then(this.theaterVenuePersistence).should().read("TVEN01");
    }

    @Test
    void testRead_NotFound() {
        BDDMockito.given(this.theaterVenuePersistence.read("NONEXISTENT"))
                .willThrow(new NotFoundException("TheaterVenue venueCode: NONEXISTENT"));

        assertThatThrownBy(() -> this.theaterVenueService.read("NONEXISTENT"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("NONEXISTENT");
    }
}
