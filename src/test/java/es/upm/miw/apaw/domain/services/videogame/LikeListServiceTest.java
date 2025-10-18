package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
public class LikeListServiceTest {

    @Autowired
    private LikeListService likeListService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testObtainSectorByMobile() {

        // Simular el usuario devuelto por el cliente REST
        BDDMockito.given(this.userRestClient.readByMobile(any(String.class)))
                .willAnswer(invocation ->
                        UserDto.builder()
                                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                                .mobile(invocation.getArgument(0))
                                .firstName("mock")
                                .build()
                );

        List<String> expectedSectors = List.of("sector0", "sector1");

        List<String> actualSectors = this.likeListService.obtainSectorsByMobile("123123123");

        assertEquals(expectedSectors, actualSectors);
    }


}






