package es.upm.miw.apaw.domain.services.vehicle;

import es.upm.miw.apaw.domain.persistenceports.vehicle.ExtraPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class ExtraServiceIT {

    @Autowired
    private ExtraService extraService;

    @MockitoBean
    private ExtraPersistence extraPersistence;

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        this.extraService.delete(id);
        BDDMockito.then(this.extraPersistence).should().delete(id);
    }
}
