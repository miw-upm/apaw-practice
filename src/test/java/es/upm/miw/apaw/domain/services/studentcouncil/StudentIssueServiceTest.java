package es.upm.miw.apaw.domain.services.studentcouncil;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentIssuePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StudentIssueServiceTest {

    @Mock
    private StudentIssuePersistence studentIssuePersistence;

    @InjectMocks
    private StudentIssueService studentIssueService;

    public StudentIssueServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudentIssue() {
        StudentIssue issue = StudentIssue.builder()
                .id(UUID.randomUUID())
                .statement("Unit test issue")
                .reportDate(LocalDateTime.now())
                .closed(false)
                .urgency(1)
                .build();

        when(studentIssuePersistence.create(issue)).thenReturn(issue);

        StudentIssue created = studentIssueService.createStudentIssue(issue);

        assertThat(created).isNotNull();
        assertThat(created.getStatement()).isEqualTo("Unit test issue");

        verify(studentIssuePersistence, times(1)).create(issue);
    }
}