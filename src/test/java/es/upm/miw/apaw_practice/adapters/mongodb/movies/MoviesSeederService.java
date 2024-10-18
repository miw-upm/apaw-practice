package es.upm.miw.apaw_practice.adapters.mongodb.movies;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.ActorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.AwardRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.MovieRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.StudioRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.ActorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.AwardEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.MovieEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.StudioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class MoviesSeederService {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private AwardRepository awardRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private StudioRepository studioRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("---*****--- Movies Initial Load ---*****---");
        AwardEntity[] awards = {
                new AwardEntity("Oscar_1981_BestActor", "Oscar", "Best Actor", LocalDate.of(1981, 3, 31)),
                new AwardEntity("Oscar_2011_BestAchievementInSoundMixing", "Oscar", "Best Achievement in Sound Mixing", LocalDate.of(2011, 2, 27)),
                new AwardEntity("GoldenGlobe_2023_BestActress", "Golden Globe", "Best Actress", LocalDate.of(2023, 1, 10)),
                new AwardEntity("Oscar_2022_BestSupportingActor", "Oscar", "Best Supporting Actor", LocalDate.of(2022, 3, 27)),
                new AwardEntity("Oscar_2020_BestDirector", "Oscar", "Best Director", LocalDate.of(2020, 2, 9)),
                new AwardEntity("GoldenGlobe_1995_BestActor", "Golden Globe", "Best Actor", LocalDate.of(1995, 1, 15)),
                new AwardEntity("BAFTA_2020_BestDirector", "BAFTA", "Best Director", LocalDate.of(2020, 2, 2))
        };
        this.awardRepository.saveAll(Arrays.asList(awards));

        ActorEntity[] actors = {
                new ActorEntity("Tom Hardy", "Edward Thomas Hardy", true, LocalDate.of(1977, 9, 15)),
                new ActorEntity("Scarlett Johansson", "Scarlett Ingrid Johansson", true, LocalDate.of(1984, 11, 22)),
                new ActorEntity("Robert De Niro", "Robert Anthony De Niro", false, LocalDate.of(1943, 8, 17)),
                new ActorEntity("Natalie Portman", "Natalie Hershlag", true, LocalDate.of(1981, 6, 9)),
                new ActorEntity("Jack Nicholson", "John Joseph Nicholson", false, LocalDate.of(1937, 4, 22)),
                new ActorEntity("Samuel L. Jackson", "Samuel Leroy Jackson", true, LocalDate.of(1948, 12, 21)),
                new ActorEntity("Leonardo DiCaprio", "Leonardo Wilhelm DiCaprio", true, LocalDate.of(1974, 11, 11)),
                new ActorEntity("Kate Winslet", "Kate Elizabeth Winslet", true, LocalDate.of(1975, 10, 5))
        };
        this.actorRepository.saveAll(Arrays.asList(actors));

        // Relacionar actores y premios a películas
        Set<ActorEntity> actorsForInception = new HashSet<>(Set.of(actors[0], actors[3], actors[5]));
        Set<ActorEntity> actorsForTitanic = new HashSet<>(Set.of(actors[0], actors[7]));
        Set<ActorEntity> actorsForPulpFiction = new HashSet<>(Set.of(actors[5], actors[1]));
        Set<ActorEntity> actorsForTaxiDriver = new HashSet<>(Set.of(actors[2]));
        Set<ActorEntity> actorsForHeat = new HashSet<>(Set.of(actors[2], actors[3]));
        Set<ActorEntity> actorsForMarsAttacks = new HashSet<>(Set.of(actors[3], actors[4]));
        Set<ActorEntity> actorsForTheRevenant = new HashSet<>(Set.of(actors[6], actors[0]));
        Set<ActorEntity> actorsForTheProfessional = new HashSet<>(Set.of(actors[3]));

        MovieEntity[] movies = {
                new MovieEntity("tt1375666", "Inception", new BigDecimal("292587330"), LocalDate.of(2010, 7, 8), actorsForInception, awards[2]),
                new MovieEntity("tt7654321", "Titanic", new BigDecimal("500000000"), LocalDate.of(2020, 5, 10), actorsForTitanic, awards[4]),
                new MovieEntity("tt0110912", "Pulp Fiction", new BigDecimal("107928762"), LocalDate.of(1994, 10, 14), actorsForPulpFiction, null),
                new MovieEntity("tt0075314", "Taxi Driver", new BigDecimal("28262576"), LocalDate.of(1976, 2, 8), actorsForTaxiDriver, null),
                new MovieEntity("tt0113277", "Heat", new BigDecimal("187436818"), LocalDate.of(1995, 12, 15), actorsForHeat, awards[0]),
                new MovieEntity("tt0116996", "Mars Attacks!", new BigDecimal("101371017"), LocalDate.of(1996, 12, 13), actorsForMarsAttacks, null),
                new MovieEntity("tt1663202", "The Revenant", new BigDecimal("528966675"), LocalDate.of(2015, 12, 25), actorsForTheRevenant, awards[0]),
                new MovieEntity("tt0110413", "The Professional", new BigDecimal("47000000"), LocalDate.of(1994, 11, 18), actorsForTheProfessional, awards[2])
        };
        this.movieRepository.saveAll(Arrays.asList(movies));

        Set<MovieEntity> moviesForWarnerBros = new HashSet<>(Set.of(movies[0], movies[2], movies[4]));
        Set<MovieEntity> moviesForUniversal = new HashSet<>(Set.of(movies[3]));
        Set<MovieEntity> moviesForMiramax = new HashSet<>(Set.of(movies[1]));

        StudioEntity[] studios = {
                new StudioEntity("Warner Bros", LocalDate.of(1923, 4, 4), new BigDecimal("10000000000"), moviesForWarnerBros),
                new StudioEntity("Universal Pictures", LocalDate.of(1912, 4, 30), new BigDecimal("8000000000"), moviesForUniversal),
                new StudioEntity("Miramax", LocalDate.of(1979, 12, 31), new BigDecimal("500000000"), moviesForMiramax)
        };
        this.studioRepository.saveAll(Arrays.asList(studios));
    }

    public void deleteAll() {
        this.studioRepository.deleteAll();
        this.movieRepository.deleteAll();
        this.awardRepository.deleteAll();
        this.actorRepository.deleteAll();
    }
}
