package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.WebAccountEntity;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class WebAccountRepositoryIT {

    @Autowired
    private WebAccountRepository webAccountRepository;

    @Test
    void testFindByUserId(){
        UUID userId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");
        List<WebAccountEntity> webAccounts = webAccountRepository.findByUserId(userId);
        assertFalse(webAccounts.isEmpty());
        assertEquals(2, webAccounts.size());
    }
}
