package es.upm.miw.apaw.domain.persistenceports.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Attendee;

public interface AttendeePersistence {

    Attendee readByEmailAddress(String emailAddress);

    void delete(String emailAddress);
}