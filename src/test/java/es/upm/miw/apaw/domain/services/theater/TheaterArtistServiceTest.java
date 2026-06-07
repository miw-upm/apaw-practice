package es.upm.miw.apaw.domain.services.theater;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterArtistPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class TheaterArtistServiceTest {

    @Autowired
    private TheaterArtistService theaterArtistService;

    @MockitoBean
    private TheaterArtistPersistence theaterArtistPersistence;

    @Test
    void testCreate() {
        BDDMockito.given(this.theaterArtistPersistence.existsByArtistCode("TART99"))
                .willReturn(false);
        BDDMockito.given(this.theaterArtistPersistence.create(Mockito.any(TheaterArtist.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        TheaterArtist artist = TheaterArtist.builder()
                .artistCode("TART99")
                .artistFullName("Test Artist")
                .artistBirthDate(LocalDate.of(1990, 1, 1))
                .artistFee(new BigDecimal("500.00"))
                .artistActive(true)
                .build();

        TheaterArtist result = this.theaterArtistService.create(artist);

        assertThat(result.getArtistCode()).isEqualTo("TART99");
        assertThat(result.getArtistFullName()).isEqualTo("Test Artist");
        BDDMockito.then(this.theaterArtistPersistence).should().create(artist);
    }

    @Test
    void testCreate_ConflictException() {
        BDDMockito.given(this.theaterArtistPersistence.existsByArtistCode("TART99"))
                .willReturn(true);

        TheaterArtist artist = TheaterArtist.builder()
                .artistCode("TART99")
                .artistFullName("Duplicate Artist")
                .artistBirthDate(LocalDate.of(1990, 1, 1))
                .artistFee(new BigDecimal("500.00"))
                .artistActive(true)
                .build();

        assertThatThrownBy(() -> this.theaterArtistService.create(artist))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("TART99");
    }

    @Test
    void testDelete() {
        this.theaterArtistService.delete("TART01");
        BDDMockito.then(this.theaterArtistPersistence).should().delete("TART01");
    }

    @Test
    void testDelete_NotFound() {
        BDDMockito.doThrow(new NotFoundException("TheaterArtist artistCode: NONEXISTENT"))
                .when(this.theaterArtistPersistence).delete("NONEXISTENT");

        assertThatThrownBy(() -> this.theaterArtistService.delete("NONEXISTENT"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("NONEXISTENT");
    }
}
