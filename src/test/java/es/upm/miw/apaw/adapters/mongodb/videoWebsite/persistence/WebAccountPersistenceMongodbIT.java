package es.upm.miw.apaw.adapters.mongodb.videoWebsite.persistence;

import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.WebAccountPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class WebAccountPersistenceMongodbIT {

    @Autowired
    private WebAccountPersistence webAccountPersistence;

    @Test
    void testFindById(){
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010");
        WebAccount webAccount = webAccountPersistence.findById(id);
        assertNotNull(webAccount);
        assertThat(webAccount.getId()).isEqualTo(id);
        assertThat(webAccount.getUserName()).isEqualTo("Account 1");
    }
}
