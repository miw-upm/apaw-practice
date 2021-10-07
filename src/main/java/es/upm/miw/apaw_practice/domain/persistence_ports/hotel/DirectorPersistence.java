package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Director;

import java.util.List;

public interface DirectorPersistence {
    List<Director> readEmails();
}
