package es.upm.miw.apaw.adapters.mongodb.recruiting.persistance;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.PositionRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import es.upm.miw.apaw.domain.persistenceports.recruiting.PositionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("positionPersistence")
public class PositionPersistenceMongodb implements PositionPersistence {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionPersistenceMongodb(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position create(Position position) {
        // Next reference
        int nextReference = this.positionRepository
                .findTopByOrderByReferenceDesc()
                .map(p -> p.getReference() + 1)
                .orElse(1); // Starting by 1

        position.setReference(nextReference);

        // Persist
        return this.positionRepository
                .save(new PositionEntity(position))
                .toPosition();
    }

    @Override
    public Position read(Integer reference) {
        PositionEntity positionEntity = this.positionRepository.findAll().stream()
                .filter(p -> p.getReference().equals(reference))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Position with reference " + reference + " not found"));
        return positionEntity.toPosition();
    }

    @Override
    public void update(Integer reference, Position position) {
        PositionEntity positionEntity = this.positionRepository.findAll().stream()
                .filter(p -> p.getReference().equals(reference))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Position with reference " + reference + " not found"));

        // Updates the modifiable fields
        positionEntity.setName(position.getName());
        positionEntity.setDescription(position.getDescription());
        positionEntity.setAnnualSalary(position.getAnnualSalary());
        positionEntity.setBonusSalary(position.getBonusSalary());
        positionEntity.setNumVacancies(position.getNumVacancies());

        this.positionRepository.save(positionEntity);
    }
}