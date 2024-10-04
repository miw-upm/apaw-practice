package es.upm.miw.apaw_practice.adapters.mongodb.military;

import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.SoldierRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.SoldierEntity;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MilitarySeederService {
    @Autowired
    private SoldierRepository soldierRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("----------- Military Initial Load -----------");
        SoldierEntity[] soldiers = {
                new SoldierEntity(new Soldier("12345678Z", "Juan García Pérez", "Private", LocalDate.of(1990, 5, 10))),
                new SoldierEntity(new Soldier("87654321F", "María López Sánchez", "Sergeant", LocalDate.of(1985, 8, 15))),
                new SoldierEntity(new Soldier("11223344J", "Carlos Fernández Gómez", "Lieutenant", LocalDate.of(1993, 12, 20))),
                new SoldierEntity(new Soldier("55667788S", "Lucía Martínez Rodríguez", "Corporal", LocalDate.of(1988, 3, 5))),
                new SoldierEntity(new Soldier("Z2334455N", "Michael Müller", "Captain", LocalDate.of(1980, 11, 30)))
        };
        this.soldierRepository.saveAll(Arrays.asList(soldiers));
    }

    public void deleteAll() {
        this.soldierRepository.deleteAll();
    }
}
