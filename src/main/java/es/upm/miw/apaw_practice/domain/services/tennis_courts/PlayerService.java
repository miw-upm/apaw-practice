package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.CourtNumberList;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.PlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerPersistence playerPersistence;

    @Autowired
    public PlayerService(PlayerPersistence playerPersistence) {
        this.playerPersistence = playerPersistence;
    }

    public void create(Player player) {
        this.playerPersistence.create(player);
    }

    public void updateEquipment(String dni, List<Equipment> equipmentList){
        this.playerPersistence.updateEquipment(dni, equipmentList);
    }

    public CourtNumberList getOccupiedCourts(String name){
        CourtNumberList numbers = this.playerPersistence.getOccupiedCourts(name);
        if(numbers.getNumbers().isEmpty()){
            throw new NotFoundException("No se ha encontrado ninguna pista ocupada mediante el nombre " + name);
        }
        return numbers;
    }
}
