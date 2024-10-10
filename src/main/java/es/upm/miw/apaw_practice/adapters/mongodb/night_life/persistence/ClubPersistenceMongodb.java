package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ClubRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ReservationEntity;
import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import es.upm.miw.apaw_practice.domain.models.night_life.Reservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ClubPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ClubEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("clubPersistence")
public class ClubPersistenceMongodb implements ClubPersistence {
    private final ClubRepository clubRepository;
    private final ReservationRepository reservationRepository;
    @Autowired
    public ClubPersistenceMongodb(ClubRepository clubRepository, ReservationRepository reservationRepository) {
        this.clubRepository = clubRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Stream<Club> readAll() {
        List<ReservationEntity> allReservations = this.reservationRepository.findAll();
        return this.clubRepository
                .findAll().stream()
                .map(clubEntity -> {
                    List<Reservation> reservationsForClub = allReservations.stream()
                            .filter(reservationEntity -> reservationEntity.getClubEntity().getId().equals(clubEntity.getId()))
                            .map(ReservationEntity::toReservation)
                            .toList();
                    Club club = clubEntity.toClub();
                    club.setReservations(reservationsForClub);
                    return club;
                });
    }

    @Override
    public Optional<Club> findByOwnerEntity_Name(String ownerName) {
        return this.clubRepository.findByOwnerEntity_Name(ownerName)
                .map(ClubEntity::toClub);
    }
}
