package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.football.Stadium;
import es.upm.miw.apaw.domain.persistenceports.football.StadiumPersistence;
import org.springframework.stereotype.Service;

@Service
public class StadiumService {

    private final StadiumPersistence stadiumPersistence;

    public StadiumService(StadiumPersistence stadiumPersistence) {
        this.stadiumPersistence = stadiumPersistence;
    }

    public Stadium create(Stadium stadium) {
        if (stadium.getOfficialName() == null || stadium.getOfficialName().isBlank()) {
            throw new BadRequestException("Official name cannot be null or blank");
        }
        if (stadium.getCapacity() == null || stadium.getCapacity() <= 0) {
            throw new BadRequestException("Capacity must be greater than 0");
        }
        if (this.stadiumPersistence.existsByOfficialName(stadium.getOfficialName())) {
            throw new ConflictException("Stadium already exists: " + stadium.getOfficialName());
        }
        return this.stadiumPersistence.save(stadium);
    }

    public Stadium readByOfficialName(String name) {
        return this.stadiumPersistence.findByOfficialName(name)
                .orElseThrow(() -> new NotFoundException("Stadium name: " + name));
    }
}
