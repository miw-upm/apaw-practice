package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class ReservationEntity {
    @Id
    private String id;
    private String ownerName;
    private LocalDateTime date;
    private Integer duration;
    @DBRef
    private List<PlayerEntity> players;

    public ReservationEntity(){
        //empty from framework
    }

    public ReservationEntity(String ownerName, LocalDateTime date, Integer duration, List<PlayerEntity> players){
        this.id = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.date = date;
        this.duration = duration;
        this.players = players;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }

    public static List<Player> toPlayerList(List<PlayerEntity> playerEntityList){
        return playerEntityList.stream()
                .map(PlayerEntity::toPlayer)
                .collect(Collectors.toList());
    }

    public Reservation toReservation(){
        return Reservation.builder(this.date, this.duration)
                .name(this.ownerName)
                .players(toPlayerList(this.players))
                .court(null)
                .build();
    }
}
