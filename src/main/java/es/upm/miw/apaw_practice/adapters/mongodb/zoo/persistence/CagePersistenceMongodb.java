package es.upm.miw.apaw_practice.adapters.mongodb.zoo.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.CageRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CageEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.CageFumigation;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.CagePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository("cagePersistence")
public class CagePersistenceMongodb implements CagePersistence {

    private final CageRepository cageRepository;

    @Autowired
    public CagePersistenceMongodb(CageRepository cageRepository) {
        this.cageRepository = cageRepository;
    }

    @Override
    public void updateNextFumigation(ZooEntity zooEntity, CageFumigation cageFumigation) {
        List<CageEntity> cages = this.cageRepository.findByZoo(zooEntity)
                .filter(cage -> cage.getNextFumigation().equals(cageFumigation.getOldFumigation()))
                .collect(Collectors.toList());
        cages.forEach(cage -> cage.setNextFumigation(cageFumigation.getNewFumigation()));
        this.cageRepository.saveAll(cages);
    }
}
