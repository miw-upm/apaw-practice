package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.DirectorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.DirectorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("directorPersistence")
public class DirectorPersistenceMongodb implements DirectorPersistence {

    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorPersistenceMongodb(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Director> readEmails() {
      return this.directorRepository.findAll().stream()
                .map(DirectorEntity::toDirector)
                .map(Director::ofEmail)
                .collect(Collectors.toList());
    }

    @Override
    public List<Hotel> getHotelsByDirector(String dni) {
        Director director = this.directorRepository
                .findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Director DNI: " + dni))
                .toDirector();

        return director.getHotelList();
    }
}
