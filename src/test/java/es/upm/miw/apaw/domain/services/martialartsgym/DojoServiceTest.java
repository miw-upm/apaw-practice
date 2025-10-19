package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.DojoPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
class DojoServiceTest {

    @MockitoBean
    private DojoPersistence dojoPersistence;

    @Autowired
    private DojoService dojoService;

    @Test
    void testCreateDojo() {
        Dojo input = Dojo.builder()
                .cadastralReference("D-8001")
                .city("Valencia")
                .foundationDate(LocalDate.of(2023, 2, 5))
                .build();

        Dojo expected = Dojo.builder()
                .cadastralReference("D-8001")
                .city("Valencia")
                .foundationDate(LocalDate.of(2023, 2, 5))
                .build();

        BDDMockito.given(dojoPersistence.create(input)).willReturn(expected);

        Dojo result = dojoService.create(input);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("D-8001", result.getCadastralReference());
        Assertions.assertEquals("Valencia", result.getCity());
        Assertions.assertEquals(LocalDate.of(2023, 2, 5), result.getFoundationDate());
    }
}
