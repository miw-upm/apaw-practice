package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorPersistence {
    List<Director> readEmails();
}
