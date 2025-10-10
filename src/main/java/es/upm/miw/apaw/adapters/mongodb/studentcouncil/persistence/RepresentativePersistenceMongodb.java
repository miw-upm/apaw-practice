package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.RepresentativeRepository;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.RepresentativeEntity;
import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.RepresentativePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository("representativePersistence")
public class RepresentativePersistenceMongodb implements RepresentativePersistence {

    private final RepresentativeRepository representativeRepository;

    @Autowired
    public RepresentativePersistenceMongodb(RepresentativeRepository representativeRepository) {
        this.representativeRepository = representativeRepository;
    }

    @Override
    public Stream<Representative> readAll() {
        return this.representativeRepository.findAll()
                .stream()
                .map(RepresentativeEntity::toRepresentative);
    }
}
