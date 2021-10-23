package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.CourtNumberList;
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
    public static final String NAME = "/{name}";
    public static final String COURTS = "/courts";
    public static final String OCCUPIED = "/occupied";

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

    @GetMapping(PlayerResource.NAME + PlayerResource.COURTS + PlayerResource.OCCUPIED)
    public CourtNumberList get(@PathVariable String name){
        return this.playerService.getOccupiedCourts(name);
    }

    
}
