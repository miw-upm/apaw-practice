package es.upm.miw.apaw.domain.services.studentcouncil;


import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentCouncilEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentCouncilPersistence;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
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

    @Test
    void testSumResourcesByStatement() {
        // --- Arrange ---
        StudentIssue issue = StudentIssue.builder()
                .id(UUID.randomUUID())
                .statement("Problem1")
                .urgency(1)
                .closed(false)
                .build();

        Representative rep = Representative.builder()
                .joinDate(LocalDateTime.now())
                .responsibility("President")
                .representative(new UserDto(UUID.randomUUID(), "600000000", "John Doe"))
                .topics(List.of(issue))
                .build();

        StudentCouncil council = StudentCouncil.builder()
                .id(UUID.randomUUID())
                .council("Council1")
                .site("Site A")
                .resources(new BigDecimal("50000.00"))
                .representatives(List.of(rep))
                .build();

        when(persistence.readAll()).thenReturn(Stream.of(council));

        // --- Act ---
        BigDecimal result = service.sumResourcesByStatement("Problem1");

        // --- Assert ---
        assertEquals(new BigDecimal("50000.00"), result);
        verify(persistence).readAll();
    }

    @Test
    void testSumResourcesByStatementNoMatch() {
        when(persistence.readAll()).thenReturn(Stream.empty());

        BigDecimal result = service.sumResourcesByStatement("NonExistingStatement");

        assertEquals(BigDecimal.ZERO, result);
        verify(persistence).readAll();
    }

}