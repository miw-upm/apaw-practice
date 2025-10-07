package es.upm.miw.apaw.adapters.mongodb.airport.daos;

import es.upm.miw.apaw.adapters.mongodb.airport.entities.AirlineEntity;
import es.upm.miw.apaw.adapters.mongodb.airport.entities.BoardingGateEntity;
import es.upm.miw.apaw.adapters.mongodb.airport.entities.FlightEntity;
import es.upm.miw.apaw.adapters.mongodb.airport.entities.PlaneEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class AirportSeeder {

    private final AirlineRepository airlineRepository;
    private final BoardingGateRepository boardingGateRepository;
    private final FlightRepository flightRepository;
    private final PlaneRepository planeRepository;

    @Autowired
    public AirportSeeder(
            AirlineRepository airlineRepository,
            BoardingGateRepository boardingGateRepository,
            FlightRepository flightRepository,
            PlaneRepository planeRepository
    ) {
        this.airlineRepository = airlineRepository;
        this.boardingGateRepository = boardingGateRepository;
        this.flightRepository = flightRepository;
        this.planeRepository = planeRepository;
    }

    public void seedDatabase() {
        log.warn("------- Airport Initial Load -----------");

        // 1) Boarding gates
        BoardingGateEntity[] boardingGates = {
                BoardingGateEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .number("A01")
                        .terminal("T1")
                        .opened(true)
                        .build(),
                BoardingGateEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .number("B02")
                        .terminal("T2")
                        .opened(false)
                        .build(),
                BoardingGateEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .number("C03")
                        .terminal("T3")
                        .opened(false)
                        .build(),
                BoardingGateEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .number("A02")
                        .terminal("T1")
                        .opened(true)
                        .build(),
        };
        this.boardingGateRepository.saveAll(Arrays.asList(boardingGates));

        // 2) Planes
        PlaneEntity[] planes = {
                PlaneEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1001"))
                        .registrationNumber("EC-MAD")
                        .model("A320neo")
                        .seatCount(186)
                        .createdAt(LocalDateTime.of(2024,1, 1, 12, 0))
                        .manufacturer("Airbus")
                        .build(),
                PlaneEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1002"))
                        .registrationNumber("EC-BCN")
                        .model("B737-8")
                        .seatCount(189)
                        .createdAt(LocalDateTime.of(2024,4, 1, 12, 0))
                        .manufacturer("Boeing")
                        .build(),
                PlaneEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1003"))
                        .registrationNumber("EC-VAL")
                        .model("A350-900")
                        .seatCount(331)
                        .createdAt(LocalDateTime.of(2024,7, 1, 12, 0))
                        .manufacturer("Airbus")
                        .build(),
                PlaneEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1004"))
                        .registrationNumber("EC-SVQ")
                        .model("B787-9 Dreamliner")
                        .seatCount(296)
                        .createdAt(LocalDateTime.of(2025,1, 1, 12, 0))
                        .manufacturer("Boeing")
                        .build()
        };
        this.planeRepository.saveAll(Arrays.asList(planes));


        // 3) Users (pilot + passengers) — usas tu UserDto del dominio
        UUID[] pilots = {
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"),
        };



        List<UUID> passegers0 = new ArrayList<>(List.of(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")
        ));
        List<UUID> passegers1 = new ArrayList<>(List.of(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004")
        ));
        List<UUID> passegers2 = new ArrayList<>(List.of(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005")
        ));
        List<UUID> passegers3 = new ArrayList<>(List.of(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005")
        ));
        List<List<UUID>> passengerLists = new ArrayList<>(List.of(
                passegers0,
                passegers1,
                passegers2,
                passegers3
        ));

        // 4) Flights (referencian gate y plane por @DBRef)
        FlightEntity[] flights = {
                FlightEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                        .departureTime(LocalDateTime.of(2025,10, 5, 12, 0))
                        .arrivalTime(LocalDateTime.of(2025,10, 5, 16, 0))
                        .destination("BCN")
                        .boardingGate(boardingGates[0])
                        .plane(planes[2])
                        .passengersIds(passengerLists.get(0))
                        .pilotId(pilots[1])
                        .build(),
                FlightEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3001"))
                        .departureTime(LocalDateTime.now().plusDays(2).withHour(12).withMinute(20))
                        .arrivalTime(LocalDateTime.now().plusDays(2).withHour(14).withMinute(55))
                        .destination("MAD")
                        .boardingGate(boardingGates[1])
                        .plane(planes[3])
                        .passengersIds(passengerLists.get(1))
                        .pilotId(pilots[2])
                        .build(),
                FlightEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3002"))
                        .departureTime(LocalDateTime.now().plusDays(3).withHour(18).withMinute(15))
                        .arrivalTime(LocalDateTime.now().plusDays(3).withHour(20).withMinute(35))
                        .destination("PMI")
                        .boardingGate(boardingGates[2])
                        .plane(planes[0])
                        .passengersIds(passengerLists.get(2))
                        .pilotId(pilots[3])
                        .build(),
                FlightEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3003"))
                        .departureTime(LocalDateTime.now().plusDays(4).withHour(7).withMinute(35))
                        .arrivalTime(LocalDateTime.now().plusDays(4).withHour(10).withMinute(10))
                        .destination("LIS")
                        .boardingGate(boardingGates[3])
                        .plane(planes[1])
                        .passengersIds(passengerLists.get(3))
                        .pilotId(pilots[0])
                        .build(),
        };
        this.flightRepository.saveAll(Arrays.asList(flights));


        // 5) Airline con lista de flights referenciada
        AirlineEntity[] airlines = {
                AirlineEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4000"))
                        .name("UPM Airlines")
                        .code("UP")
                        .country("ES")
                        .flights(List.of())
                        .build(),
                AirlineEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4001"))
                        .name("Iberia Express")
                        .code("IB")
                        .country("ES")
                        .flights(List.of(flights[0], flights[1]))
                        .build(),
                AirlineEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4002"))
                        .name("Air Europa")
                        .code("UX")
                        .country("ES")
                        .flights(List.of(flights[2]))
                        .build(),
                AirlineEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4003"))
                        .name("Vueling")
                        .code("VY")
                        .country("ES")
                        .flights(List.of(flights[3]))
                        .build()
        };
        this.airlineRepository.saveAll(Arrays.asList(airlines));

        log.warn("        ------- airport");
    }

    public void deleteAll() {
        this.airlineRepository.deleteAll();
        this.flightRepository.deleteAll();
        this.boardingGateRepository.deleteAll();
        this.planeRepository.deleteAll();
    }
}
