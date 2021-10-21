package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.PlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.ReservationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.ReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("reservationPersistence")
public class ReservationPersistenceMongoDB implements ReservationPersistence {

    private final ReservationRepository reservationRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public ReservationPersistenceMongoDB(ReservationRepository reservationRepository, PlayerRepository playerRepository){
        this.reservationRepository = reservationRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Reservation read(String ownerName, LocalDateTime date){
        return this.findByOwnerName(ownerName, date)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado la reserva con el nombre " + ownerName + " y la fecha [" + date + "]"))
                .toReservation();
    }

    @Override
    public void delete(String ownerName, LocalDateTime date) {
        findByOwnerName(ownerName, date)
                .ifPresent(this.reservationRepository::delete);
    }

    @Override
    public Stream<Player> updatePlayerList(String ownerName, LocalDateTime date, Reservation reservation){
        ReservationEntity reservationEntity = this.findByOwnerName(ownerName, date)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado la reserva buscada"));
        List<Player> originalPlayers = ReservationEntity.toPlayerList(reservationEntity.getPlayers());
        List<Player> playersToInclude = new ArrayList<>();
        for (Player playerDNIContainer: reservation.getPlayers()) {
            playersToInclude.add(this.playerRepository.findByDni(playerDNIContainer.getDNI())
                    .orElseThrow(() -> new NotFoundException("El DNI aportado no pertenece a ningún jugador registrado"))
                    .toPlayer()
            );
        }
        originalPlayers.addAll(playersToInclude);
        reservationEntity.setPlayersFromPlayerList(originalPlayers);
        return this.reservationRepository.save(reservationEntity)
                .getPlayersDNIs().stream();
    }

    private Optional<ReservationEntity> findByOwnerName(String ownerName, LocalDateTime date){
        List<ReservationEntity> reservationEntityList = this.reservationRepository.findByOwnerName(ownerName)
                .filter(entity -> entity.getDate().isEqual(date)).collect(Collectors.toList());
        if(reservationEntityList.size() > 1){
            throw new ConflictException("Existen dos reservas con el mismo dueño y fecha");
        }
        return reservationEntityList.stream().findFirst();
    }
}
