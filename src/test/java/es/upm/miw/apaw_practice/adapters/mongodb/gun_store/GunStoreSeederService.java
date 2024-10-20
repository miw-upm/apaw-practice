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
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                new GunEntity(0, new BigDecimal("999.99"), "Glock 17", "Glock", Arrays.asList(accesories[0], accesories[1]), compatibleAmmos[0]),
                new GunEntity(1, new BigDecimal("1249.99"), "Colt M1911", "Colt", Arrays.asList(accesories[2], accesories[3]), compatibleAmmos[1]),
                new GunEntity(2, new BigDecimal("749.99"), "AR-15", "Armalite", Arrays.asList(accesories[4], accesories[2], accesories[3]), compatibleAmmos[2]),
                new GunEntity(3, new BigDecimal("1299.99"), "AK-47", "Kalashnikov", Arrays.asList(accesories[3], accesories[0]), compatibleAmmos[3]),
                new GunEntity(4, new BigDecimal("599.99"), "Remington 870", "Remington", Arrays.asList(accesories[4], accesories[2]), compatibleAmmos[4]),
                new GunEntity(5, new BigDecimal("849.99"), "Beretta 92FS", "Beretta", Arrays.asList(accesories[0], accesories[1]), compatibleAmmos[0]),
                new GunEntity(6, new BigDecimal("699.99"), "Mossberg 500", "Mossberg", Arrays.asList(accesories[4], accesories[3]), compatibleAmmos[4]),
                new GunEntity(7, new BigDecimal("2999.99"), "FN SCAR 17", "FN Herstal", Arrays.asList(accesories[2], accesories[0]), compatibleAmmos[3]),
                new GunEntity(8, new BigDecimal("2999.99"), "FN SCAR 17 - 2", "FN Herstal", Arrays.asList(accesories[2], accesories[0]), compatibleAmmos[3]),
                new GunEntity(9, new BigDecimal("2999.99"), "FN SCAR 17 - 3", "FN Herstal", Arrays.asList(accesories[2], accesories[0]), compatibleAmmos[3]),
        };
        this.gunRepository.saveAll(Arrays.asList(guns));

        SetupEntity[] setups = {
                new SetupEntity(0, new BigDecimal("2249.98"), Arrays.asList(guns[0], guns[1])),
                new SetupEntity(1, new BigDecimal("2599.98"), Arrays.asList(guns[2], guns[3])),
                new SetupEntity(2, new BigDecimal("1849.98"), Arrays.asList(guns[5], guns[0])),
                new SetupEntity(3, new BigDecimal("3999.98"), Arrays.asList(guns[6], guns[3])),
                new SetupEntity(4, new BigDecimal("4299.98"), Arrays.asList(guns[7], guns[2]))
        };

        this.setupRepository.saveAll(Arrays.asList(setups));
    }

    public void deleteAll() {
        this.setupRepository.deleteAll();
        this.gunRepository.deleteAll();
        this.compatibleAmmoRepository.deleteAll();
        this.accesoryRepository.deleteAll();
    }

    public static Setup getNewDummySetup() {
        Accesory accesory1 = new Accesory(7, "Scope", new BigDecimal("100.00"));
        Accesory accesory2 = new Accesory(8, "Silencer", new BigDecimal("150.00"));
        CompatibleAmmo ammo = new CompatibleAmmo(7, new BigDecimal("20.00"), "9mm");
        List<Accesory> accesories = new ArrayList<>();
        accesories.add(accesory1);
        accesories.add(accesory2);
        Gun gun1 = new Gun(new BigDecimal("500.00"), "Pistol", "Manufacturer A", accesories, ammo);
        Gun gun2 = new Gun(new BigDecimal("600.00"), "Rifle", "Manufacturer B", accesories, ammo);
        gun1.setGunId(7);
        gun2.setGunId(8);
        List<Gun> guns = new ArrayList<>();
        guns.add(gun1);
        guns.add(gun2);
        return new Setup(6, LocalDate.now(), new BigDecimal("1000"), guns);
    }

    public static Gun getNewDummyGun(Integer gunId) {
        Accesory accesory1 = new Accesory(7, "Scope", new BigDecimal("100.00"));
        CompatibleAmmo ammo = new CompatibleAmmo(7, new BigDecimal("20.00"), "9mm");
        List<Accesory> accesories = new ArrayList<>();
        accesories.add(accesory1);
        Gun gun = new Gun(new BigDecimal("500.00"), "Pistol", "Manufacturer A", accesories, ammo);
        gun.setGunId(gunId);
        return gun;
    }
}
