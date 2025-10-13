package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("test")
class RepresentativePersistenceMongodbIT {

    @Autowired
    private RepresentativePersistenceMongodb representativePersistence;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testFindAll() {
        List<Representative> representatives = this.representativePersistence.readAll().toList();
        assertThat(representatives).isNotEmpty();
        assertThat(representatives.getFirst().getResponsibility()).isNotBlank();
        assertThat(representatives.getFirst().getJoinDate()).isNotNull();
    }

    @Test
    void testFindUserMobilesByReplyReason() {
        when(userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")))
                .thenReturn(new UserDto(
                        UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"),
                        "600123456", "John"));

        when(userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")))
                .thenReturn(new UserDto(
                        UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"),
                        "600654321", "Mary"));

        List<String> result = representativePersistence.findUserMobilesByReplyReason("Reply1");
        assertThat(result).contains("600123456", "600654321");
    }
}
