package es.upm.miw.apaw_practice.adapters.mongodb.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.ConsoleCompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.ConsoleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleCompanyrEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlayerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGamerEntity;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoGameSeederService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ConsoleCompanyRepository consoleCompanyRepository;

    @Autowired
    private VideoGameRepository videoGameRepository;

    @Autowired
    private ConsoleRepository consoleRepository;

    public void seedDatabase(){
        LogManager.getLogger(this.getClass()).warn("------- VideoGame Initial Load -----------");
        VideoGamerEntity[] videogames = {
                new VideoGamerEntity(new VideoGame("Halo",2,true, LocalDate.of(2012,9,16))),
                new VideoGamerEntity(new VideoGame("Call of Duty",2,false, LocalDate.of(2022,4,12))),
                new VideoGamerEntity(new VideoGame("Dante Inferno",2,true, LocalDate.of(2002,12,6))),
                new VideoGamerEntity(new VideoGame("Zelda",1,false, LocalDate.of(2015,1,30))),
                new VideoGamerEntity(new VideoGame("Roblox",360,false, LocalDate.of(2015,1,30))),
        };
        this.videoGameRepository.saveAll(Arrays.asList(videogames));

        ConsoleEntity[] consoles = {
                new ConsoleEntity("Xbox", 9875456464646L,LocalDate.of(1985,9,6),List.of(videogames[0],videogames[1]),true),
                new ConsoleEntity("GameCube", 123456789L,LocalDate.of(2020,9,26),List.of(videogames[2],videogames[3]),true),
                new ConsoleEntity("PlayStation", 956464163134L,LocalDate.of(2005,9,2),List.of(videogames[1]),true),
                new ConsoleEntity("Nintendo", 123456786566565659L,LocalDate.of(1995,9,30),List.of(videogames[3],videogames[0]),true),
                new ConsoleEntity("SuperNintendo", 123456786566565659L,LocalDate.of(1995,9,30),List.of(videogames[4]),true),
        };
        this.consoleRepository.saveAll(Arrays.asList(consoles));

        PlayerEntity[] players = {
                new PlayerEntity("Luis",29,true,LocalDate.of(1995,9,16),consoles[0]),
                new PlayerEntity("Maria",28,false,LocalDate.of(1996,6,17),consoles[1]),
                new PlayerEntity("Julia",60,true,LocalDate.of(1961,3,14),consoles[2]),
                new PlayerEntity("Melba",23,false,LocalDate.of(1961,5,12),consoles[3]),
                new PlayerEntity("Nelson",23,false,LocalDate.of(1961,5,12),consoles[4]),
        };
        this.playerRepository.saveAll(Arrays.asList(players));

        ConsoleCompanyrEntity[] consoleCompanies = {
                new ConsoleCompanyrEntity("Sony","www.Sony.com",2000,false,LocalDate.of(2022,12,31),List.of(consoles[0],consoles[1])),
                new ConsoleCompanyrEntity("Bungie","www.burys.com",4000,true,LocalDate.of(2002,1,15),List.of(consoles[2],consoles[3])),
                new ConsoleCompanyrEntity("Tuto","www.tremulous.com",6000,true,LocalDate.of(2002,1,15),List.of(consoles[2],consoles[3])),
                new ConsoleCompanyrEntity("Nanoby","www.nanoby.com",6000,true,LocalDate.of(2002,1,15),List.of(consoles[4])),
        };
        this.consoleCompanyRepository.saveAll(Arrays.asList(consoleCompanies));
    }

    public void deleteAll(){
        this.consoleCompanyRepository.deleteAll();
        this.playerRepository.deleteAll();
        this.consoleRepository.deleteAll();
        this.videoGameRepository.deleteAll();
    }

}
