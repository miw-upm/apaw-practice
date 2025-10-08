package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.ApplicationPersistenceMongodb;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationPersistenceMongodbIT {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationPersistenceMongodb applicationPersistence;

    private UUID id;
    private ApplicationEntity entity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        entity = ApplicationEntity.builder().id(id).build();
    }

    @Test
    void testReadByIdSuccess() {
        when(applicationRepository.findById(id)).thenReturn(Optional.of(entity));

        Application app = applicationPersistence.readById(id);

        assertNotNull(app);
        assertEquals(id, app.getId());
        verify(applicationRepository).findById(id);
    }

    @Test
    void testReadByIdNotFound() {
        when(applicationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> applicationPersistence.readById(id));
        verify(applicationRepository).findById(id);
    }
}
