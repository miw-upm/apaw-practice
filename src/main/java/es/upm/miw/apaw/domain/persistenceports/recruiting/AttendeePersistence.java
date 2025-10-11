package es.upm.miw.apaw.domain.persistenceports.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeePersistence {

    Attendee readByEmailAddress(String emailAddress);

    void delete(String emailAddress);
}