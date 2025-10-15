package es.upm.miw.apaw.domain.services.videogame;


import es.upm.miw.apaw.domain.persistenceports.videogame.VideogamePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class VideogameServiceTest {

    @Autowired
    private VideogameService videogameService;

    @MockitoBean
    private VideogamePersistence videogamePersistence;

    @Test
    void testSetOnlineByGenre_callsPersistenceCorrectly() {
        // Act
        videogameService.setOnlineByGenre("action", false);

        // Assert
        verify(videogamePersistence, times(1))
                .updateOnlineByGenre("action", false);
        verifyNoMoreInteractions(videogamePersistence);
    }

}
