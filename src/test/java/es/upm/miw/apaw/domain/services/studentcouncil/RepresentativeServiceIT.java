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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class RepresentativeServiceIT {

    @Autowired
    private RepresentativeService representativeService;

    @MockitoBean
    private UserRestClient userRestClient;


    @Test
    void testFindUserMobilesByReplyReason() {
        when(userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")))
                .thenReturn(new UserDto(UUID.randomUUID(), "600111222", "John"));
        when(userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")))
                .thenReturn(new UserDto(UUID.randomUUID(), "600333444", "Mary"));

        List<String> mobiles = representativeService.findUserMobilesByReplyReason("Reply1");

        assertThat(mobiles)
                .isNotEmpty()
                .contains("600111222", "600333444")
                .doesNotContain("999999999");
    }
    @Test
    void testGetAllRepresentatives() {
        when(userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")))
                .thenReturn(new UserDto(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"),
                        "600123456", "John"));
        when(userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")))
                .thenReturn(new UserDto(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"),
                        "600654321", "Mary"));

        List<Representative> representatives = representativeService.getAllRepresentatives();

        assertThat(representatives).isNotEmpty();
        assertThat(representatives)
                .anyMatch(rep -> rep.getRepresentative().getFirstName().equals("John")
                        && rep.getRepresentative().getMobile().equals("600123456"))
                .anyMatch(rep -> rep.getRepresentative().getFirstName().equals("Mary")
                        && rep.getRepresentative().getMobile().equals("600654321"));
    }
}

