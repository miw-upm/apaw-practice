package es.upm.miw.apaw.domain.ports.out.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.CarUsageReport;
import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.model.carreservation.ReservationFindCriteria;

import java.util.List;

public interface ReservationGateway {
    Reservation create(Reservation reservation);

    List<CarUsageReport> findCarUsageReport();

    List<Reservation> find(ReservationFindCriteria criteria);
}
