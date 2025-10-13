package es.upm.miw.apaw.domain.services.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Extra;
import es.upm.miw.apaw.domain.persistenceports.vehicle.ExtraPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
class ExtraServiceIT {

    @Autowired
    private ExtraService extraService;

    @MockitoBean
    private ExtraPersistence extraPersistence;

    @Test
    void testUpdatePrices() {
        Extra oldExtra = Extra.builder()
                .category("Seguridad")
                .description("Airbags laterales")
                .price(new BigDecimal("250.00"))
                .build();

        Extra newExtra = Extra.builder()
                .category("Seguridad")
                .description("Airbags laterales")
                .price(new BigDecimal("300.00"))
                .build();

        BDDMockito.given(this.extraPersistence.readByCategoryAndDescription("Seguridad", "Airbags laterales"))
                .willReturn(oldExtra);
        BDDMockito.given(this.extraPersistence.update(any(Extra.class)))
                .willReturn(newExtra);

        this.extraService.updatePrices(Stream.of(newExtra));

        BDDMockito.then(this.extraPersistence).should().update(any(Extra.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        this.extraService.delete(id);
        BDDMockito.then(this.extraPersistence).should().delete(id);
    }
}
