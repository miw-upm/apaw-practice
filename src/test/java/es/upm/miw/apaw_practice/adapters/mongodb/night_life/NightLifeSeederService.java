package es.upm.miw.apaw_practice.adapters.mongodb.night_life;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ClubRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.CustomerNightLifeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.OwnerNightLifeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ClubEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.CustomerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.OwnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ReservationEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class NightLifeSeederService {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private CustomerNightLifeRepository customerRepository;
    @Autowired
    private OwnerNightLifeRepository ownerRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- NightLife Initial Load -----------");

        OwnerEntity[] owners = {
                new OwnerEntity("Owner1", "123456789", "owner1@example.com"),
                new OwnerEntity("Owner2", "234567891", "owner2@example.com"),
                new OwnerEntity("Owner3", "345678912", "owner3@example.com")
        };
        this.ownerRepository.saveAll(Arrays.asList(owners));

        ClubEntity[] clubs = {
                new ClubEntity("Cuenca Club", 400, false, owners[0]),
                new ClubEntity("Kapital", 800, false, owners[0]),
                new ClubEntity("Teatro Barcelo", 400, false, owners[1])
        };
        this.clubRepository.saveAll(Arrays.asList(clubs));

        CustomerEntity[] customers = {
                new CustomerEntity("Pepe","11223344","example@upm.es"),
                new CustomerEntity("Juan", "22334455", "janesmith@example.com"),
                new CustomerEntity("Raul","33445566","raul@upm.es"),
                new CustomerEntity("Sara", "44556677", "sara@example.com")
        };
        this.customerRepository.saveAll(Arrays.asList(customers));

        ReservationEntity[] reservations = {
                new ReservationEntity(LocalDate.now().plusDays(1),new BigDecimal("50.00"),2,clubs[0],Arrays.asList(customers[0], customers[1])),
                new ReservationEntity(LocalDate.now().plusDays(2),new BigDecimal("50.00"),2,clubs[1],Arrays.asList(customers[2], customers[3])),

        };
        this.reservationRepository.saveAll(Arrays.asList(reservations));
    }

    public void deleteAll() {
        this.reservationRepository.deleteAll();
        this.customerRepository.deleteAll();
        this.clubRepository.deleteAll();
        this.ownerRepository.deleteAll();
    }
}
