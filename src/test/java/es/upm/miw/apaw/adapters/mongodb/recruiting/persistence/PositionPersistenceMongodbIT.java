package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.PositionRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.PositionPersistenceMongodb;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PositionPersistenceMongodbIT {

    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private PositionPersistenceMongodb positionPersistenceMongodb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_WithExistingPositions_ShouldIncrementReference() {
        // Creating some data test
        PositionEntity existing = PositionEntity.builder()
                .id(UUID.randomUUID())
                .reference(5)
                .name("Senior Developer")
                .annualSalary(new BigDecimal("60000"))
                .numVacancies(1)
                .build();

        when(positionRepository.findTopByOrderByReferenceDesc()).thenReturn(Optional.of(existing));

        // Mock: save returns the same converted object
        ArgumentCaptor<PositionEntity> captor = ArgumentCaptor.forClass(PositionEntity.class);
        when(positionRepository.save(any(PositionEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // New position without reference (max++ must be chosen)
        Position newPosition = Position.builder()
                .name("New Backend Developer")
                .description("API developer")
                .annualSalary(new BigDecimal("50000"))
                .bonusSalary(new BigDecimal("3000"))
                .numVacancies(2)
                .build();

        // Act
        Position saved = positionPersistenceMongodb.create(newPosition);

        // Assert
        verify(positionRepository).findTopByOrderByReferenceDesc();
        verify(positionRepository).save(captor.capture());

        PositionEntity savedEntity = captor.getValue();
        assertEquals(6, savedEntity.getReference()); // 5 + 1
        assertEquals(6, saved.getReference());
        assertEquals("New Backend Developer", saved.getName());
    }

    @Test
    void testCreate_WhenNoExistingPositions_ShouldStartWithReference1() {
        // Mock: no records in the DB
        when(positionRepository.findTopByOrderByReferenceDesc()).thenReturn(Optional.empty());
        when(positionRepository.save(any(PositionEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Position newPosition = Position.builder()
                .name("First Position")
                .annualSalary(new BigDecimal("40000"))
                .numVacancies(1)
                .build();

        Position saved = positionPersistenceMongodb.create(newPosition);

        verify(positionRepository).findTopByOrderByReferenceDesc();
        verify(positionRepository).save(any(PositionEntity.class));

        assertEquals(1, saved.getReference());
        assertEquals("First Position", saved.getName());
    }
}
