package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.domain.models.apiary.Apiary;
import es.upm.miw.apaw.domain.persistenceports.apiary.ApiaryPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ActiveProfiles("test")
public class ApiaryServiceIT {

    @Autowired
    private ApiaryService apiaryService;

    @MockitoBean
    private ApiaryPersistence apiaryPersistence;

    @Test
    void testFindByLocationReturnsResults() {
        Apiary apiary1 = Apiary.builder()
                .cadastralRef("0000000-00000000-0001-XX")
                .location("Burgos")
                .rega("REGA00001")
                .hives(List.of())
                .build();

        Apiary apiary2 = Apiary.builder()
                .cadastralRef("0000000-00000000-0002-YY")
                .location("Burgos")
                .rega("REGA00002")
                .hives(List.of())
                .build();

        BDDMockito.given(apiaryPersistence.findByLocation(anyString()))
                .willReturn(Stream.of(apiary1, apiary2));

        Stream<Apiary> apiaries = apiaryService.findByLocation("Burgos");

        assertThat(apiaries).isNotNull();
        assertThat(apiaries.toList()).hasSize(2)
                .allSatisfy(apiary -> assertThat(apiary.getLocation()).isEqualTo("Burgos"));
    }

    @Test
    void testFindByLocationReturnsEmpty() {
        BDDMockito.given(apiaryPersistence.findByLocation(anyString()))
                .willReturn(Stream.empty());

        Stream<Apiary> apiaries = apiaryService.findByLocation("Valencia");

        assertThat(apiaries).isEmpty();
    }
}
