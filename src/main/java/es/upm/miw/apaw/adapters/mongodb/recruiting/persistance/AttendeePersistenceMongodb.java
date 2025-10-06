package es.upm.miw.apaw.adapters.mongodb.recruiting.persistance;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.persistenceports.recruiting.AttendeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("attendeePersistence")
public class AttendeePersistenceMongodb implements AttendeePersistence {

    private final AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeePersistenceMongodb(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public Attendee readByEmailAddress(String emailAddress) {
        return this.attendeeRepository
                .findByEmailAddress(emailAddress)
                .orElseThrow(() -> new NotFoundException("No existing Email Address: " + emailAddress))
                .toAttendee();
    }
}
