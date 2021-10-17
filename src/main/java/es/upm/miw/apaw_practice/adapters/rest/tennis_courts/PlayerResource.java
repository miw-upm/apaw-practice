package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.services.tennis_courts.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PlayerResource.PLAYERS)
public class PlayerResource {

    public static final String PLAYERS = "/players";
    public static final String DNI = "/{dni}";
    public static final String EQUIPMENTS = "/equipments";

    private final PlayerService playerService;

    @Autowired
    public PlayerResource(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping
    public void create(@RequestBody Player player){
        this.playerService.create(player);
    }

    @PutMapping(PlayerResource.DNI + PlayerResource.EQUIPMENTS)
    public void updateEquipment(@PathVariable String dni, @RequestBody List<Equipment> equipmentList){
        this.playerService.updateEquipment(dni, equipmentList);
    }

    
}
