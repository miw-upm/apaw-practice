package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.CourtRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.PlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.CourtEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.PlayerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.CourtNumberList;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.PlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("playerPersistence")
public class PlayerPersistenceMongoDB implements PlayerPersistence {

    private final PlayerRepository playerRepository;
    private final CourtRepository courtRepository;

    @Autowired
    public PlayerPersistenceMongoDB(PlayerRepository playerRepository, CourtRepository courtRepository){
        this.playerRepository = playerRepository;
        this.courtRepository = courtRepository;
    }

    @Override
    public void create(Player player) {
        this.playerRepository
                .save(new PlayerEntity(player));
    }

    @Override
    public Player read(String dni){
        return this.findByDni(dni)
                .toPlayer();
    }

    @Override
    public void updateEquipment(String dni, List<Equipment> equipmentList){
        PlayerEntity player = this.findByDni(dni);
        player.setEquipmentEntityListFromEquipment(equipmentList);
        this.playerRepository.save(player);
    }

    @Override
    public CourtNumberList getOccupiedCourts(String name){
        return new CourtNumberList(this.courtRepository.findAll().stream()
                .filter(courtEntity -> courtEntity.getOccupied() && courtEntity.getReservations().stream()
                        .anyMatch(reservationEntity -> reservationEntity.getPlayers().stream()
                                .anyMatch(playerEntity -> playerEntity.getName().equals(name))))
                .map(CourtEntity::getNumber).collect(Collectors.toList()));
    }

    private PlayerEntity findByDni(String dni){
        return this.playerRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Player DNI: " + dni));
    }
}
