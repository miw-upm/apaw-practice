package es.upm.miw.apaw_practice.adapters.mongodb.movies.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.AwardRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.AwardEntity;
import es.upm.miw.apaw_practice.domain.models.movies.Award;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.AwardPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("awardPersistence")
public class AwardPersistenceMongodb implements AwardPersistence {

    private final AwardRepository awardRepository;

    @Autowired
    public AwardPersistenceMongodb(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    @Override
    public void create(Award award) {
        this.awardRepository.save(AwardEntity.fromAward(award));
    }

    @Override
    public void delete(String nameCategoryAndYear) {
        Optional<AwardEntity> awardEntity = awardRepository.findByNameCategoryYear(nameCategoryAndYear);
        awardEntity.ifPresent(awardRepository::delete);
    }


    @Override
    public boolean existsByName(String name) {
        return this.awardRepository.findByNameCategoryYear(name).isPresent();
    }
}
