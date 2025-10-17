package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videoWebsite.*;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.AccountType;
import es.upm.miw.apaw.domain.models.videoWebsite.enums.VideoStatus;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.CommentPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testFindCommentersMobileByVideoTitle() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                                .mobile("123456789")
                                .firstName("mock").build());

        List<String> mobiles = this.commentService.findCommentersMobileByVideoTitle("title 1");
        assertEquals(1, mobiles.size());
        assertEquals(mobiles.getFirst(), "123456789");


    }
}
