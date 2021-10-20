package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.CupsRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.CupsEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.CupsPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("cupsPersistence")
public class CupsPersistenceMongodb implements CupsPersistence {
    private final CupsRepository cupsRepository;

    @Autowired
    public CupsPersistenceMongodb(CupsRepository cupsRepository) {
        this.cupsRepository = cupsRepository;
    }

    @Override
    public Cups readByCup(String cups) {
        return this.cupsRepository.findByCups(cups)
                .orElseThrow(() -> new NotFoundException("Cups with cup: " + cups))
                .toCups();
    }

    @Override
    public void update(Cups cups) {
        CupsEntity cupsEntity = this.cupsRepository
                .findByCups(cups.getCups())
                .orElseThrow(() -> new NotFoundException("Cups cup:" + cups.getCups()));
        cupsEntity.setEnergy(cupsEntity.getEnergy());
        BeanUtils.copyProperties(cups, cupsEntity, "id", "cups");
        this.cupsRepository.save(cupsEntity).toCups();
    }
}
