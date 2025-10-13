package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.RepresentativePersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


@SpringBootTest
@ActiveProfiles("test")
class RepresentativeServiceTest {

    @MockitoBean
    private RepresentativePersistence representativePersistence;

    @MockitoBean
    private UserRestClient userRestClient;

    @Autowired
    private RepresentativeService representativeService; // <-- NO crees instancia manualmente

    @Test
    void testGetAllRepresentatives() {
        UUID userId = UUID.fromString("dddddddd-bbbb-cccc-dddd-eeeeffff0001");

        Representative rep = Representative.builder()
                .joinDate(LocalDateTime.now())
                .responsibility("President")
                .representative(UserDto.builder().id(userId).build())
                .topics(List.of())
                .build();

        BDDMockito.given(representativePersistence.readAll())
                .willReturn(Stream.of(rep));

        BDDMockito.given(userRestClient.readById(userId))
                .willReturn(UserDto.builder()
                        .id(userId)
                        .firstName("mockUser")
                        .mobile("123456789")
                        .build());

        List<Representative> reps = representativeService.getAllRepresentatives();

        Assertions.assertFalse(reps.isEmpty());
        Assertions.assertEquals("mockUser", reps.getFirst().getRepresentative().getFirstName());
        Assertions.assertEquals("123456789", reps.getFirst().getRepresentative().getMobile());
    }
}
