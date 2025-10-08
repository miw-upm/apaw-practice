package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.persistenceports.recruiting.AttendeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendeeService {

    private final AttendeePersistence attendeePersistence;

    @Autowired
    public AttendeeService(AttendeePersistence attendeePersistence) {
        this.attendeePersistence = attendeePersistence;
    }

    public Attendee read(String emailAddress) {
        return this.attendeePersistence.readByEmailAddress(emailAddress);
    }

    public void delete(String emailAddress) {
        this.attendeePersistence.delete(emailAddress);
    }
}
