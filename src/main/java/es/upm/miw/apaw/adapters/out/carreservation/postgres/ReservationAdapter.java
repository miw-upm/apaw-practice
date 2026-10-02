package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.ports.out.carreservation.ReservationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationAdapter implements ReservationGateway {
    private final ReservationRepository reservationRepository;
}