package es.upm.miw.apaw_practice.adapters.mongodb.gun_store;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos.AccesoryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos.CompatibleAmmoRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos.GunRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos.SetupRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.AccesoryEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.CompatibleAmmoEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.GunEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.SetupEntity;
import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.models.gun_store.CompatibleAmmo;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class GunStoreSeederService {

    @Autowired
    private SetupRepository setupRepository;
    @Autowired
    private GunRepository gunRepository;
    @Autowired
    private CompatibleAmmoRepository compatibleAmmoRepository;
    @Autowired
    private AccesoryRepository accesoryRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Gun Store Initial Load -----------");
        AccesoryEntity[] accesories = {
                new AccesoryEntity(new Accesory(1, "Scope", new BigDecimal("199.99"))),
                new AccesoryEntity(new Accesory(2, "Grip", new BigDecimal("49.99"))),
                new AccesoryEntity(new Accesory(3, "Laser Sight", new BigDecimal("89.99"))),
                new AccesoryEntity(new Accesory(4, "Silencer", new BigDecimal("299.99"))),
                new AccesoryEntity(new Accesory(5, "Magazine", new BigDecimal("24.99")))
        };

        this.accesoryRepository.saveAll(Arrays.asList(accesories));

        CompatibleAmmoEntity[] compatibleAmmos = {
                new CompatibleAmmoEntity(new CompatibleAmmo(1, new BigDecimal("15.99"), "9mm")),
                new CompatibleAmmoEntity(new CompatibleAmmo(2, new BigDecimal("24.99"), ".45 ACP")),
                new CompatibleAmmoEntity(new CompatibleAmmo(3, new BigDecimal("18.99"), ".223 Remington")),
                new CompatibleAmmoEntity(new CompatibleAmmo(4, new BigDecimal("29.99"), "7.62x39mm")),
                new CompatibleAmmoEntity(new CompatibleAmmo(5, new BigDecimal("12.99"), "12 Gauge"))
        };
        this.compatibleAmmoRepository.saveAll(Arrays.asList(compatibleAmmos));

        GunEntity[] guns = {
                new GunEntity(new BigDecimal("999.99"), "Glock 17", "Glock", Arrays.asList(accesories[0], accesories[1]), compatibleAmmos[0]),
                new GunEntity(new BigDecimal("1249.99"), "Colt M1911", "Colt", Arrays.asList(accesories[2], accesories[3]), compatibleAmmos[1]),
                new GunEntity(new BigDecimal("749.99"), "AR-15", "Armalite", Arrays.asList(accesories[4], accesories[2], accesories[3]), compatibleAmmos[2]),
                new GunEntity(new BigDecimal("1299.99"), "AK-47", "Kalashnikov", Arrays.asList(accesories[3], accesories[0]), compatibleAmmos[3]),
                new GunEntity(new BigDecimal("599.99"), "Remington 870", "Remington", Arrays.asList(accesories[4], accesories[2]), compatibleAmmos[4])
        };
        this.gunRepository.saveAll(Arrays.asList(guns));

        SetupEntity[] setups = {
                new SetupEntity(new BigDecimal("2249.98"), Arrays.asList(guns[0], guns[1])),
                new SetupEntity(new BigDecimal("2599.98"), Arrays.asList(guns[2], guns[3]))
        };

        this.setupRepository.saveAll(Arrays.asList(setups));
    }

    public void deleteAll() {
        this.setupRepository.deleteAll();
        this.gunRepository.deleteAll();
        this.compatibleAmmoRepository.deleteAll();
        this.accesoryRepository.deleteAll();
    }
}
