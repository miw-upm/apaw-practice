package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.ports.out.carreservation.ReservationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ReservationAdapter implements ReservationGateway {
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Reservation create(Reservation reservation) {
        ReservationEntity reservationEntity = new ReservationEntity(reservation);

        ReservationEntity savedEntity = this.reservationRepository.save(reservationEntity);

        return savedEntity.toDomain();
    }
}