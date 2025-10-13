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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest
@ActiveProfiles("test")
class RepresentativeServiceIT {

    @Autowired
    private RepresentativeService representativeService;

    @MockitoBean
    private UserRestClient userRestClient;

    @MockitoBean
    private RepresentativePersistence representativePersistence;

    @Test
    void testGetAllRepresentativesUnit() {
        UUID userId = UUID.randomUUID();

        Representative rep = Representative.builder()
                .joinDate(LocalDateTime.now())
                .responsibility("President")
                .representative(UserDto.builder().id(userId).build())
                .topics(new ArrayList<>())
                .build();

        // Stubeamos el mock
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

