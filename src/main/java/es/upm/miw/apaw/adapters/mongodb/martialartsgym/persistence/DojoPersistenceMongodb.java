package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.DojoRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.DojoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
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
    @Override
    public BigDecimal findTotalUnitCostByCity(String city) {
        DojoEntity dojo = this.dojoRepository.findByCity(city)
                .orElseThrow(() -> new NotFoundException("Dojo not found in city: " + city));

        if (dojo.getEquipment() == null || dojo.getEquipment().isEmpty()) {
            return BigDecimal.ZERO;
        }

        return dojo.getEquipment().stream()
                .map(EquipmentEntity::getUnitCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
