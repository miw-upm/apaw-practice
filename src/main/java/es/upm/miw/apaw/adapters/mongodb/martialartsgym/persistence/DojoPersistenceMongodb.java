package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.DojoRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.ClassSessionEntity;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.DojoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DojoPersistenceMongodb implements DojoPersistence {

    private final DojoRepository dojoRepository;

    @Autowired
    public DojoPersistenceMongodb(DojoRepository dojoRepository) {
        this.dojoRepository = dojoRepository;
    }

    @Override
    public Dojo create(Dojo dojo) {
        DojoEntity entity = DojoEntity.builder()
                .cadastralReference(dojo.getCadastralReference())
                .city(dojo.getCity())
                .foundationDate(dojo.getFoundationDate())
                .equipment(dojo.getEquipment() == null ? List.of() :
                        dojo.getEquipment().stream()
                                .map(eq -> EquipmentEntity.builder()
                                        .barCode(eq.getBarCode())
                                        .itemLabel(eq.getItemLabel())
                                        .unitCost(eq.getUnitCost())
                                        .build())
                                .toList())
                .classSessions(List.of())
                .build();

        this.dojoRepository.save(entity);
        return entity.toDojo();
    }
}
