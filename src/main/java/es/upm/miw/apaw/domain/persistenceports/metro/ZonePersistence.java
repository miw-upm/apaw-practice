package es.upm.miw.apaw.domain.persistenceports.metro;

import es.upm.miw.apaw.domain.models.metro.Zone;

public interface ZonePersistence {
    Zone getByType(String type);
    Zone update(String type, Zone zone);
    boolean existType(String type);
}
