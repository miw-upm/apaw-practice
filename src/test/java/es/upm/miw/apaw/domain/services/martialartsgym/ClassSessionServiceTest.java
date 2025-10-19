package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.persistenceports.martialartsgym.ClassSessionPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class ClassSessionServiceTest {

    @MockitoBean
    private ClassSessionPersistence classSessionPersistence;

    @Autowired
    private ClassSessionService classSessionService;

    @Test
    void testDeleteClassSession() {
        Integer referenceCode = 4567;

        Mockito.doNothing().when(classSessionPersistence).delete(referenceCode);

        classSessionService.delete(referenceCode);

        Mockito.verify(classSessionPersistence, Mockito.times(1)).delete(referenceCode);
    }
}
