package es.upm.miw.apaw.adapters.mongodb.recruiting.persistance;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.persistenceports.recruiting.AttendeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        AttendeeEntity attendee = attendeeRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new NotFoundException("No existing Email Address: " + emailAddress));

        // Read all the application and delete the attendee from the meetings
        List<ApplicationEntity> applications = applicationRepository.findAll();

        for (ApplicationEntity app : applications) {
            for (MeetingEntity meeting : app.getMeetingList()) {
                meeting.getAttendees().removeIf(att ->
                        att != null && emailAddress.equals(att.getEmailAddress())
                );
            }
            applicationRepository.save(app);
        }

        // Remove the attendee from the global repository
        attendeeRepository.delete(attendee);
    }
}