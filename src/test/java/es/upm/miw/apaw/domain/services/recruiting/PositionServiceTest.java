package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.PositionRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.PositionPersistenceMongodb;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private PositionPersistenceMongodb positionPersistenceMongodb;

    private Position position;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        position = Position.builder()
                .name("Java Developer")
                .description("Backend developer using Spring Boot")
                .annualSalary(new BigDecimal("60000"))
                .bonusSalary(new BigDecimal("5000"))
                .numVacancies(3)
                .build();
    }

    @Test
    void testCreatePosition_WhenExistingReferencesPresent() {
        // Arrange: the greatest reference is 5
        PositionEntity existingEntity = PositionEntity.builder()
                .id(UUID.randomUUID())
                .reference(5)
                .name("Old Position")
                .build();

        when(positionRepository.findTopByOrderByReferenceDesc()).thenReturn(Optional.of(existingEntity));
        when(positionRepository.save(any(PositionEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Position result = positionPersistenceMongodb.create(position);

        // Assert
        System.out.println("✅ Reference auto generated (there are existing records): " + result.getReference());
        assertEquals(6, result.getReference()); // 5 + 1
        verify(positionRepository).findTopByOrderByReferenceDesc();
        verify(positionRepository).save(any(PositionEntity.class));
    }

    @Test
    void testCreatePosition_WhenNoExistingReferences() {
        // Collection is empty
        when(positionRepository.findTopByOrderByReferenceDesc()).thenReturn(Optional.empty());
        when(positionRepository.save(any(PositionEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Position result = positionPersistenceMongodb.create(position);

        // Assert
        System.out.println("✅ Reference auto generated (without any previous records): " + result.getReference());
        assertEquals(1, result.getReference()); // Starts in 1
        verify(positionRepository).findTopByOrderByReferenceDesc();
        verify(positionRepository).save(any(PositionEntity.class));
    }
}