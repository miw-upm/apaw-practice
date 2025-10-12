package es.upm.miw.apaw.domain.services.studentcouncil;


import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentCouncilPersistence;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentCouncilServiceTest {

    private final StudentCouncilPersistence persistence = mock(StudentCouncilPersistence.class);
    private final StudentCouncilService service = new StudentCouncilService(persistence);

    @Test
    void testUpdateResources() {
        UUID id = UUID.randomUUID();
        BigDecimal resources = BigDecimal.valueOf(5000);

        StudentCouncil council = new StudentCouncil(id, "ETSII", "Madrid", BigDecimal.valueOf(3000), null);

        when(persistence.readById(id)).thenReturn(Optional.of(council));
        when(persistence.update(any(StudentCouncil.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StudentCouncil updated = service.updateResources(id, resources);

        assertEquals(resources, updated.getResources());
        verify(persistence).update(any(StudentCouncil.class));
    }

    @Test
    void testUpdateResourcesNotFound() {
        UUID id = UUID.randomUUID();
        when(persistence.readById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateResources(id, BigDecimal.TEN));
    }
}