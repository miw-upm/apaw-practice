package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import es.upm.miw.apaw.domain.persistenceports.recruiting.ApplicationPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationServiceTest {

    @Mock
    private ApplicationPersistence applicationPersistence;

    @MockitoBean
    private UserRestClient userRestClient;

    @InjectMocks
    private ApplicationService applicationService;

    private UUID applicationId;
    private Application application;
    private List<Meeting> meetingList;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        applicationId = UUID.randomUUID();
        meetingList = List.of(
                new Meeting(LocalDateTime.now(), "https://meet.test", List.of())
        );
        application = Application.builder()
                .id(applicationId)
                .meetingList(List.of())
                .build();
    }

    @Test
    void testUpdateMeetingsSuccess() {
        when(applicationPersistence.readById(applicationId)).thenReturn(application);
        when(applicationPersistence.update(any(Application.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Application updated = applicationService.updateMeetings(applicationId, meetingList);

        assertNotNull(updated);
        assertEquals(meetingList, updated.getMeetingList());
        verify(applicationPersistence).readById(applicationId);
        verify(applicationPersistence).update(any(Application.class));
    }

    @Test
    void testUpdateMeetingsThrowsExceptionWhenNotFound() {
        when(applicationPersistence.readById(applicationId))
                .thenThrow(new RuntimeException("Application not found"));

        Exception ex = assertThrows(RuntimeException.class,
                () -> applicationService.updateMeetings(applicationId, meetingList));

        assertTrue(ex.getMessage().contains("Application not found"));
        verify(applicationPersistence, never()).update(any(Application.class));
    }
}
