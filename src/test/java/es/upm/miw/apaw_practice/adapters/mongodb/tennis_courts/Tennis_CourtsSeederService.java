package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.*;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.*;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.util.Arrays;
import java.util.List;

@Service
public class Tennis_CourtsSeederService {

    @Autowired
    private CourtRepository courtRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Tennis Courts Initial Load -----------");
        EquipmentEntity[] equipment = {
            new EquipmentEntity("Racquet", 2, new BigDecimal("5.0")),
            new EquipmentEntity("Balls", 5, new BigDecimal("1.5")),
            new EquipmentEntity("Racquet", 1, new BigDecimal("5.0")),
            new EquipmentEntity("Racquet", 1, new BigDecimal("5.0")),
            new EquipmentEntity("Balls", 3, new BigDecimal("1.5"))
        };
        PlayerEntity[] players = {
                new PlayerEntity("00000001R","Paco", "Lopez", 35, List.of(equipment[0])),
                new PlayerEntity("00000002R","Javier", "Gutierrez", 50, List.of(equipment[1])),
                new PlayerEntity("00000003R","Pepe", "Salgado", 20, List.of(equipment[2], equipment[4])),
                new PlayerEntity("00000004R","Carlos", "Alonso", 63, List.of(equipment[3])),
                new PlayerEntity("00000005R","Nacho", "Murillo", 47, List.of()),
                new PlayerEntity("00000006R","Rob", "Herrera", 17, List.of()),
                new PlayerEntity("00000007R","Jesús", "", 36, List.of()),
                new PlayerEntity("00000008R","Pedro", "Morales", 60, List.of())
        };
        playerRepository.saveAll(Arrays.asList(players));
        LocalDate date = LocalDate.of(2021,9,30);
        ReservationEntity[] reservation = {
                new ReservationEntity("Paco", date.atTime(11,0),2, List.of(players[0], players[1])),
                new ReservationEntity("Nacho", date.atTime(11,0),1, List.of(players[4], players[6])),
                new ReservationEntity("Pepe", date.atTime(13,0),2, List.of(players[2], players[3])),
                new ReservationEntity("Rob", date.atTime(12,0),1, List.of(players[5], players[7])),
                new ReservationEntity("Pedro", date.atTime(12,0),1, List.of(players[7], players[4])),
                new ReservationEntity("Paco", date.atTime(18,0),1, List.of(players[0], players[1]))
        };
        reservationRepository.saveAll(Arrays.asList(reservation));
        CourtEntity[] courts = {
            new CourtEntity(1, new BigDecimal("10.5"),false, List.of(reservation[0], reservation[2])),
            new CourtEntity(2, new BigDecimal("10.5"),true, List.of(reservation[1], reservation[5])),
            new CourtEntity(3, new BigDecimal("11.0"),true, List.of(reservation[3])),
            new CourtEntity(4, new BigDecimal("11.0"),false, List.of(reservation[4]))
        };
        courtRepository.saveAll(Arrays.asList(courts));

    }

    public void deleteAll() {
        courtRepository.deleteAll();
        reservationRepository.deleteAll();
        playerRepository.deleteAll();
    }
}
