package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import es.upm.miw.apaw.domain.persistenceports.recruiting.ApplicationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService {

    private final ApplicationPersistence applicationPersistence;

    @Autowired
    public ApplicationService(ApplicationPersistence applicationPersistence) {
        this.applicationPersistence = applicationPersistence;
    }

    public Application updateMeetings(UUID id, List<Meeting> meetingList) {
        Application application = this.applicationPersistence.readById(id);
        application.setMeetingList(meetingList);
        return this.applicationPersistence.update(application);
    }
}