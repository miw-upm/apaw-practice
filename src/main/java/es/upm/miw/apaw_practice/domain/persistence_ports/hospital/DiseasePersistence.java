package es.upm.miw.apaw_practice.domain.persistence_ports.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Disease;

public interface DiseasePersistence {

    Disease updateDescription(String alias, String description);
}
