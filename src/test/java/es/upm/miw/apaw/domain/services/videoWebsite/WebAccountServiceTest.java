package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
public class WebAccountServiceTest {

    @Autowired
    private WebAccountService webAccountService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testObtainTotalViewsByMobile(){
        BDDMockito.given(this.userRestClient.readByMobile(any(String.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                                .mobile(invocation.getArgument(0))
                                .firstName("mock").build());

        assertEquals(80000, this.webAccountService.obtainTotalViewsByMobile("123456789"));
    }
}
