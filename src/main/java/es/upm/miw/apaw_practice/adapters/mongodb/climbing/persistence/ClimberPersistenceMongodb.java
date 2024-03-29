package es.upm.miw.apaw_practice.adapters.mongodb.climbing.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.climbing.daos.ClimberRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.climbing.entities.ClimberEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.climbing.Climber;
import es.upm.miw.apaw_practice.domain.persistence_ports.climbing.ClimberPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("climberPersistence")
public class ClimberPersistenceMongodb implements ClimberPersistence {

    private final ClimberRepository climberRepository;

    @Autowired
    public ClimberPersistenceMongodb(ClimberRepository climberRepository) {
        this.climberRepository = climberRepository;
    }

    @Override
    public Climber readByEmail(String email) {
        return this.climberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(" Climber email: " + email))
                .toClimber();
    }

    @Override
    public void delete(String email) {
        this.climberRepository.deleteByEmail(email);
    }

    @Override
    public List<Climber> findByLevel(String level) {
        return this.climberRepository.findAll()
                .stream()
                .filter(climberEntity -> level.equals(climberEntity.getLevel()))
                .map(ClimberEntity::toClimber)
                .toList();
    }

}
