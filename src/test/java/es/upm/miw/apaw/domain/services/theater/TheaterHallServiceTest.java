package es.upm.miw.apaw.domain.services.theater;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterHallPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class TheaterHallServiceTest {

    @Autowired
    private TheaterHallService theaterHallService;

    @MockitoBean
    private TheaterHallPersistence theaterHallPersistence;

    @Test
    void testUpdate() {
        TheaterHall hall = TheaterHall.builder()
                .hallCode("THAL01")
                .hallName("Updated Hall")
                .hallCapacity(400)
                .hallAccessible(true)
                .build();
        BDDMockito.given(this.theaterHallPersistence.update(Mockito.eq("THAL01"), Mockito.any(TheaterHall.class)))
                .willAnswer(invocation -> invocation.getArgument(1));

        TheaterHall result = this.theaterHallService.update("THAL01", hall);

        assertThat(result.getHallName()).isEqualTo("Updated Hall");
        assertThat(result.getHallCapacity()).isEqualTo(400);
        BDDMockito.then(this.theaterHallPersistence).should().update(Mockito.eq("THAL01"), Mockito.any(TheaterHall.class));
    }

    @Test
    void testUpdate_NotFound() {
        BDDMockito.given(this.theaterHallPersistence.update(Mockito.eq("NONEXISTENT"), Mockito.any(TheaterHall.class)))
                .willThrow(new NotFoundException("TheaterHall hallCode: NONEXISTENT"));

        TheaterHall hall = TheaterHall.builder()
                .hallCode("NONEXISTENT")
                .hallName("No Hall")
                .hallCapacity(0)
                .hallAccessible(false)
                .build();

        assertThatThrownBy(() -> this.theaterHallService.update("NONEXISTENT", hall))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("NONEXISTENT");
    }
}
