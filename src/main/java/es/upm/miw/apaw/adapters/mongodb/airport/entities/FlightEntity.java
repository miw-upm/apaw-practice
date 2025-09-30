package es.upm.miw.apaw.adapters.mongodb.airport.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private UUID flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String destination;

    @DBRef
    private BoardingGateEntity boardingGate;
    @DBRef
    private PlaneEntity plane;
    private List<UUID> passengersIds;
    private UUID pilotId;
}
