package es.upm.miw.apaw.adapters.mongodb.airport.entities;

import es.upm.miw.apaw.domain.models.airport.Flight;
import es.upm.miw.apaw.domain.models.airport.Plane;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class FlightEntity {
    @Id
    private UUID id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String destination;

    @DBRef
    private BoardingGateEntity boardingGate;
    @DBRef
    private PlaneEntity plane;
    private List<UUID> passengersIds;
    private UUID pilotId;

    public Flight toFlight() {
        Flight flight = new Flight();
        BeanUtils.copyProperties(this, flight);
        return flight;
    }
}
