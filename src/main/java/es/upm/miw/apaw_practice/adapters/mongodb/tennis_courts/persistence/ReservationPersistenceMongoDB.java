package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.CourtRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.PlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.ReservationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Court;
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
    private final CourtRepository courtRepository;

    @Autowired
    public ReservationPersistenceMongoDB(ReservationRepository reservationRepository, PlayerRepository playerRepository, CourtRepository courtRepository){
        this.reservationRepository = reservationRepository;
        this.playerRepository = playerRepository;
        this.courtRepository = courtRepository;
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
                    .orElseThrow(() -> new BadRequestException("El DNI aportado no pertenece a ningún jugador registrado"))
                    .toPlayer()
            );
        }
        originalPlayers.addAll(playersToInclude);
        reservationEntity.setPlayersFromPlayerList(originalPlayers);
        return this.reservationRepository.save(reservationEntity)
                .getPlayersDNIs().stream();
    }

    @Override
    public Court get(String ownerName, LocalDateTime date){
        return this.courtRepository.findAll().stream()
                .filter(courtRepository -> courtRepository.getReservations().stream()
                        .anyMatch(reservation -> reservation.getOwnerName().equals(ownerName) && reservation.getDate().isEqual(date))
                )
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se ha encontrado ninguna pista con los datos pedidos"))
                .toCourt();
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
