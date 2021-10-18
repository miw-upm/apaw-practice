package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos.DrugRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy.DrugPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("drugPersistence")
public class DrugPersistenceMongodb implements DrugPersistence {

    private final DrugRepository drugRepository;

    @Autowired
    public DrugPersistenceMongodb(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public Stream<Drug> readAll() {
        return this.drugRepository
                .findAll().stream()
                .map(DrugEntity::toDrug);
    }
}