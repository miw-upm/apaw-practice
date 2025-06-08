package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreeningPersistence;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.ScreeningRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreeningEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("screeningPersistence")
public class ScreeningPersistenceMongodb implements ScreeningPersistence {

    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningPersistenceMongodb(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public List<Screening> findAll() {
        return this.screeningRepository.findAll()
                .stream()
                .map(ScreeningPersistenceMapper::toScreening)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Screening> findByScreeningTime(String screeningTime) {
        return this.screeningRepository.findAll().stream()
                .filter(entity -> entity.getScreeningTime().equals(screeningTime))
                .findFirst()
                .map(ScreeningPersistenceMapper::toScreening);
    }

    @Override
    public Screening save(Screening screening) {
        ScreeningEntity entity = this.screeningRepository.save(ScreeningPersistenceMapper.toEntity(screening));
        return ScreeningPersistenceMapper.toScreening(entity);
    }
}