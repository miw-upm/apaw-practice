package es.upm.miw.apaw.domain.services.winery;

import es.upm.miw.apaw.domain.models.winery.Wine;
import es.upm.miw.apaw.domain.persistenceports.winery.WinePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
public class WineServiceIT {

    @Autowired
    private WineService wineService;

    @MockitoBean
    private WinePersistence winePersistence;

    @Test
    void testReadById() {
        UUID idWine = UUID.randomUUID();
        Wine wine = Wine.builder()
                .id(idWine)
                .price(new BigDecimal("25.00"))
                .name("La Rioja")
                .alcoholPercentage(13.5)
                .year(2020)
                .build();

        BDDMockito.given(this.winePersistence.readById(idWine))
                .willReturn(wine);

        Wine result = this.wineService.read(idWine);

        assertThat(result.getName()).isEqualTo("La Rioja");
        assertThat(result.getAlcoholPercentage()).isEqualTo(13.5);
        assertThat(result.getYear()).isEqualTo(2020);
        assertThat(result.getPrice()).isEqualByComparingTo("25.00");
    }

    @Test
    void testDelete() {
        UUID idWine = UUID.randomUUID();
        this.wineService.delete(idWine);
        BDDMockito.then(this.winePersistence).should().delete(idWine);
    }

    @Test
    void testUpdatePrices() {
        Wine wine = Wine.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006"))
                .price(new BigDecimal("57.90"))
                .build();

        Wine newWine = Wine.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006"))
                .price(new BigDecimal("67.50"))
                .build();

        BDDMockito.given(this.winePersistence.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006")))
                .willReturn(wine);
        BDDMockito.given(this.winePersistence.update(any(Wine.class)))
                .willReturn(newWine);

        this.wineService.updatePrices(Stream.of(newWine));

        BDDMockito.then(this.winePersistence).should().update(any(Wine.class));
    }

}
