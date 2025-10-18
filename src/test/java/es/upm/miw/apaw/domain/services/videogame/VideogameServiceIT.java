package es.upm.miw.apaw.domain.services.videogame;


import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameSeeder;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class VideogameServiceIT {

    @Autowired
    private VideogameService videogameService;

    @Test
    void testSetOnlineByGenre() {
        String genreType = "rol";
        videogameService.setOnlineByGenre(genreType,false);
        List<Videogame> rolGames = videogameService.getByGenre(genreType);
        assertThat(rolGames).extracting("online").containsOnly(false);


    }


}
