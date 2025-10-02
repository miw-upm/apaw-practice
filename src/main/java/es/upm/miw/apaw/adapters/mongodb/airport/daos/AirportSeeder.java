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
                        .gateNumber("A01")
                        .terminal("T1")
                        .opened(true)
                        .build(),
                BoardingGateEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .gateNumber("B02")
                        .terminal("T2")
                        .opened(false)
                        .build(),
                BoardingGateEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .gateNumber("C03")
                        .terminal("T3")
                        .opened(false)
                        .build(),
                BoardingGateEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .gateNumber("A02")
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
                        .createdAt(LocalDateTime.now().minusMonths(3))
                        .manufacturer("Airbus")
                        .build(),
                PlaneEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1002"))
                        .registrationNumber("EC-BCN")
                        .model("B737-8")
                        .seatCount(189)
                        .createdAt(LocalDateTime.now().minusYears(1))
                        .manufacturer("Boeing")
                        .build(),
                PlaneEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1003"))
                        .registrationNumber("EC-VAL")
                        .model("A350-900")
                        .seatCount(331)
                        .createdAt(LocalDateTime.now().minusYears(2))
                        .manufacturer("Airbus")
                        .build(),
                PlaneEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1004"))
                        .registrationNumber("EC-SVQ")
                        .model("B787-9 Dreamliner")
                        .seatCount(296)
                        .createdAt(LocalDateTime.now().minusMonths(8))
                        .manufacturer("Boeing")
                        .build()
        };
        this.planeRepository.saveAll(Arrays.asList(planes));


        // 3) Users (pilot + passengers) — usas tu UserDto del dominio
        UUID[] pilots = {
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2000"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2001"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2002"),
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2003"),
        };

        List<List<UUID>> passengerLists = new ArrayList<>();

        for (int j = 0; j < 8; j++) {
            List<UUID> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                String suffix = String.format("%012x", j * 100 + i); // 12 dígitos hex
                String uuidStr = "aaaaaaaa-bbbb-cccc-dddd-" + suffix;
                list.add(UUID.fromString(uuidStr));
            }
            passengerLists.add(list);
        }

        // 4) Flights (referencian gate y plane por @DBRef)
        FlightEntity[] flights = {
                FlightEntity.builder()
                        .flightNumber(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                        .departureTime(LocalDateTime.now().plusDays(1).withHour(9).withMinute(30))
                        .arrivalTime(LocalDateTime.now().plusDays(1).withHour(11).withMinute(15))
                        .destination("BCN")
                        .boardingGate(boardingGates[0])
                        .plane(planes[2])
                        .passengersIds(passengerLists.get(0))
                        .pilotId(pilots[1])
                        .build(),
                FlightEntity.builder()
                        .flightNumber(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3001"))
                        .departureTime(LocalDateTime.now().plusDays(2).withHour(12).withMinute(20))
                        .arrivalTime(LocalDateTime.now().plusDays(2).withHour(14).withMinute(55))
                        .destination("MAD")
                        .boardingGate(boardingGates[1])
                        .plane(planes[3])
                        .passengersIds(passengerLists.get(1))
                        .pilotId(pilots[2])
                        .build(),
                FlightEntity.builder()
                        .flightNumber(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3002"))
                        .departureTime(LocalDateTime.now().plusDays(3).withHour(18).withMinute(15))
                        .arrivalTime(LocalDateTime.now().plusDays(3).withHour(20).withMinute(35))
                        .destination("PMI")
                        .boardingGate(boardingGates[2])
                        .plane(planes[0])
                        .passengersIds(passengerLists.get(2))
                        .pilotId(pilots[3])
                        .build(),
                FlightEntity.builder()
                        .flightNumber(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3003"))
                        .departureTime(LocalDateTime.now().plusDays(4).withHour(7).withMinute(35))
                        .arrivalTime(LocalDateTime.now().plusDays(4).withHour(10).withMinute(10))
                        .destination("LIS")
                        .boardingGate(boardingGates[3])
                        .plane(planes[1])
                        .passengersIds(passengerLists.get(3))
                        .pilotId(pilots[0])
                        .build(),
                FlightEntity.builder()
                        .flightNumber(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3004"))
                        .departureTime(LocalDateTime.now().plusDays(5).withHour(6).withMinute(45))
                        .arrivalTime(LocalDateTime.now().plusDays(5).withHour(8).withMinute(55))
                        .destination("CDG")
                        .boardingGate(boardingGates[0])
                        .plane(planes[3])
                        .passengersIds(passengerLists.get(4))
                        .pilotId(pilots[0])
                        .build(),
                FlightEntity.builder()
                        .flightNumber(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3005"))
                        .departureTime(LocalDateTime.now().plusDays(6).withHour(13).withMinute(10))
                        .arrivalTime(LocalDateTime.now().plusDays(6).withHour(15).withMinute(30))
                        .destination("AMS")
                        .boardingGate(boardingGates[1])
                        .plane(planes[0])
                        .passengersIds(passengerLists.get(5))
                        .pilotId(pilots[1])
                        .build(),
                FlightEntity.builder()
                        .flightNumber(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3006"))
                        .departureTime(LocalDateTime.now().plusDays(7).withHour(16).withMinute(40))
                        .arrivalTime(LocalDateTime.now().plusDays(7).withHour(19).withMinute(5))
                        .destination("FRA")
                        .boardingGate(boardingGates[2])
                        .plane(planes[1])
                        .passengersIds(passengerLists.get(6))
                        .pilotId(pilots[2])
                        .build(),
                FlightEntity.builder()
                        .flightNumber(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3007"))
                        .departureTime(LocalDateTime.now().plusDays(8).withHour(10).withMinute(5))
                        .arrivalTime(LocalDateTime.now().plusDays(8).withHour(12).withMinute(45))
                        .destination("LHR")
                        .boardingGate(boardingGates[3])
                        .plane(planes[2])
                        .passengersIds(passengerLists.get(7))
                        .pilotId(pilots[3])
                        .build()
        };
        this.flightRepository.saveAll(Arrays.asList(flights));


        // 5) Airline con lista de flights referenciada
        AirlineEntity[] airlines = {
                AirlineEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4000"))
                        .name("UPM Airlines")
                        .code("UP")
                        .country("ES")
                        .flights(List.of(flights[0], flights[1]))
                        .build(),
                AirlineEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4001"))
                        .name("Iberia Express")
                        .code("IB")
                        .country("ES")
                        .flights(List.of(flights[2], flights[3]))
                        .build(),
                AirlineEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4002"))
                        .name("Air Europa")
                        .code("UX")
                        .country("ES")
                        .flights(List.of(flights[4], flights[5], flights[6]))
                        .build(),
                AirlineEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff4003"))
                        .name("Vueling")
                        .code("VY")
                        .country("ES")
                        .flights(List.of(flights[7]))
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
