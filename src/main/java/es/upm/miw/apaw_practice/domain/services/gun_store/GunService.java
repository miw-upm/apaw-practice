package es.upm.miw.apaw_practice.domain.services.gun_store;

import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import es.upm.miw.apaw_practice.domain.persistence_ports.gun_store.GunPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GunService {

    private final GunPersistence gunPersistence;

    @Autowired
    public GunService(GunPersistence gunPersistence) {
        this.gunPersistence = gunPersistence;
    }

    public Gun read(String name) {
        return gunPersistence.readByName(name);
    }

    public Gun update(Integer id, Gun gun) {
        return gunPersistence.update(id, gun);
    }

    public BigDecimal getAccesoryPriceSumByGunName(String name) {
        return this.gunPersistence.readByName(name)
                .getAccesories().stream()
                .map(Accesory::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
