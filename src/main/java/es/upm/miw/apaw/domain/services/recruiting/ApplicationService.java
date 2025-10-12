package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import es.upm.miw.apaw.domain.persistenceports.recruiting.ApplicationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        if (application.getStatus() == Status.Rejected) {
            throw new ConflictException("Cannot update meetings for a rejected application: " + id);
        }
        application.setMeetingList(meetingList);
        return this.applicationPersistence.update(application);
    }

    // First search: 1269
    public BigDecimal findAccumulatedAnnualSalaryByFullName(String fullName) {
        return this.applicationPersistence.findAccumulatedAnnualSalaryByFullName(fullName);
    }

    // Second search: 1270
    public List<String> findUniqueUrlsByPositionName(String name) {
        return this.applicationPersistence.findUniqueUrlsByPositionName(name);
    }
}