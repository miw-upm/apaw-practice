package es.upm.miw.apaw_practice.adapters.mongodb.night_life;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ClubRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.CustomerNightLifeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.OwnerNightLifeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ReservationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ClubEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.OwnerEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                new OwnerEntity("Owner1", "owner1@example.com", "123456789"),
                new OwnerEntity("Owner2", "owner2@example.com", "987654321")
        };
        this.ownerRepository.saveAll(Arrays.asList(owners));
        ClubEntity[] clubs = {
                new ClubEntity("Cuenca Club", 400, false, owners[0]),
                new ClubEntity("Kapital", 800, false, owners[0]),
                new ClubEntity("Teatro Barcelo", 400, false, owners[1])
        };
        this.clubRepository.saveAll(Arrays.asList(clubs));

    }

}

