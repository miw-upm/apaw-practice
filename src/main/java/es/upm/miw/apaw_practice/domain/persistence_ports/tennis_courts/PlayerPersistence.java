package es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.CourtNumberList;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;

import java.util.List;

public interface PlayerPersistence {

    void create(Player player);

    Player read(String dni);

    void updateEquipment(String dni, List<Equipment> equipmentList);

    CourtNumberList getOccupiedCourts(String name);
}
