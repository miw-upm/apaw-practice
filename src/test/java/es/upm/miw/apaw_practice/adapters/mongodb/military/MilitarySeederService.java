package es.upm.miw.apaw_practice.adapters.mongodb.military;

import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.MissionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.SoldierRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.UnitRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.WeaponRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.MissionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.SoldierEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.UnitEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.WeaponEntity;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.Weapon;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Service
public class MilitarySeederService {
    @Autowired
    private SoldierRepository soldierRepository;
    @Autowired
    private WeaponRepository weaponRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private MissionRepository missionRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("----------- Military Initial Load -----------");
        SoldierEntity[] soldiers = {
                new SoldierEntity(new Soldier("12345678Z", "Juan García Pérez", "Private", LocalDate.of(1990, 5, 10))),
                new SoldierEntity(new Soldier("87654321F", "María López Sánchez", "Private", LocalDate.of(1985, 8, 15))),
                new SoldierEntity(new Soldier("11223344J", "Carlos Fernández Gómez", "Lieutenant", LocalDate.of(1993, 12, 20))),
                new SoldierEntity(new Soldier("55667788S", "Lucía Martínez Rodríguez", "Corporal", LocalDate.of(1988, 3, 5))),
                new SoldierEntity(new Soldier("Z2334455N", "Michael Müller", "Captain", LocalDate.of(1980, 11, 30))),
                new SoldierEntity(new Soldier("33445566T", "Ana Ramírez López", "Commandant", LocalDate.of(1978, 7, 22)))
        };
        this.soldierRepository.saveAll(Arrays.asList(soldiers));
        WeaponEntity[] weapons = {
                new WeaponEntity(new Weapon("SN12345678", "Heckler & Koch", new BigDecimal("1500.50"))),
                new WeaponEntity(new Weapon("SN87654321", "Beretta", new BigDecimal("2000.75"))),
                new WeaponEntity(new Weapon("SN11223344", "Colt", new BigDecimal("1750.00"))),
                new WeaponEntity(new Weapon("SN55667788", "Beretta", new BigDecimal("1250.25"))),
                new WeaponEntity(new Weapon("SN22334455", "Indra Sistemas", new BigDecimal("16100.00")))
        };
        this.weaponRepository.saveAll(Arrays.asList(weapons));
        UnitEntity[] units = {
                new UnitEntity("1st Infantry Regiment", "Army", "Madrid", Arrays.asList(soldiers[0], soldiers[1])),
                new UnitEntity("Maritime Task Force HQ", "Navy", "Cartagena", Arrays.asList(soldiers[2], soldiers[5])),
                new UnitEntity("15th Wing", "Air Force", "Zaragoza", Arrays.asList(soldiers[3], soldiers[4]))
        };
        this.unitRepository.saveAll(Arrays.asList(units));
        MissionEntity[] missions = {
                new MissionEntity("Enduring Freedom", true, LocalDate.of(2001, 10, 7), units[0], Arrays.asList(weapons[0], weapons[1])),
                new MissionEntity("Desert Storm", true, LocalDate.of(1990, 8, 2), units[2], Arrays.asList(weapons[2], weapons[3])),
                new MissionEntity("Air Traffic Control", false, LocalDate.of(2019, 2, 25), units[2], Collections.emptyList()),
                new MissionEntity("Atalanta", true, LocalDate.of(2008, 12, 8), units[1], Arrays.asList(weapons[1], weapons[4])),
                new MissionEntity("Perejil Crisis", false, LocalDate.of(2002, 7, 11), units[1], Arrays.asList(weapons[0], weapons[2], weapons[3]))
        };
        this.missionRepository.saveAll(Arrays.asList(missions));
    }

    public void deleteAll() {
        this.soldierRepository.deleteAll();
        this.weaponRepository.deleteAll();
        this.unitRepository.deleteAll();
        this.missionRepository.deleteAll();
    }
}
