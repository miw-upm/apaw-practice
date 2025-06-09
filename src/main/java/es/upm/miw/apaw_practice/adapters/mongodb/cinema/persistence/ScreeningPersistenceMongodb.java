package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.ScreeningRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreeningEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreeningPersistence;
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
                .map(ScreeningEntity::toScreening)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Screening> findById(String id) {
        return this.screeningRepository.findById(id)
                .map(ScreeningEntity::toScreening);
    }

    @Override
    public Screening create(Screening screening) {
        ScreeningEntity entity = ScreeningEntity.fromScreening(screening);
        return this.screeningRepository.save(entity).toScreening();
    }

    @Override
    public Screening update(String id, Screening screening) {
        ScreeningEntity entity = this.screeningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screening id: " + id + " not found"));
        entity.updateFromScreening(screening);
        return this.screeningRepository.save(entity).toScreening();
    }

    @Override
    public void delete(String id) {
        this.screeningRepository.deleteById(id);
    }
}