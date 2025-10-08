package es.upm.miw.apaw.adapters.mongodb.recruiting.persistance;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.persistenceports.recruiting.AttendeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("attendeePersistence")
public class AttendeePersistenceMongodb implements AttendeePersistence {

    private final AttendeeRepository attendeeRepository;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public AttendeePersistenceMongodb(AttendeeRepository attendeeRepository, ApplicationRepository applicationRepository) {
        this.attendeeRepository = attendeeRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Attendee readByEmailAddress(String emailAddress) {
        return this.attendeeRepository
                .findByEmailAddress(emailAddress)
                .orElseThrow(() -> new NotFoundException("No existing Email Address: " + emailAddress))
                .toAttendee();
    }

    @Override
    public void delete(String emailAddress) {
        // Search for the applications which contains the Attendee
        ApplicationEntity applicationEntity = this.applicationRepository.findAll().stream()
                .filter(app -> app.getMeetingList().stream()
                        .anyMatch(meeting -> meeting.getAttendees().stream()
                                .anyMatch(att -> att.getEmailAddress().equals(emailAddress))))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Attendee email: " + emailAddress));

        // Delete the attende for each meeting
        applicationEntity.getMeetingList().forEach(meeting ->
                meeting.getAttendees().removeIf(att -> att.getEmailAddress().equals(emailAddress))
        );

        // Save the changes in the DB
        this.applicationRepository.save(applicationEntity);
    }
}
