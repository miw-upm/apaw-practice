package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.DirectorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    private final DirectorPersistence directorPersistence;

    @Autowired
    public DirectorService(DirectorPersistence directorPersistence) {
        this.directorPersistence = directorPersistence;
    }

    public List<Director> readEmails() {
        return this.directorPersistence.readEmails();
    }

    public List<Hotel> getHotelsByDirector(String dni) {
        return this.directorPersistence.getHotelsByDirector(dni);
    }
}
