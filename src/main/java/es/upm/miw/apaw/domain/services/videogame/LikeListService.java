package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import es.upm.miw.apaw.domain.persistenceports.videogame.CompanyPersistence;
import es.upm.miw.apaw.domain.persistenceports.videogame.LikeListPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class LikeListService {
    private final LikeListPersistence likeListPersistence;
    private final UserRestClient userRestClient;
    private final CompanyPersistence companyPersistence;

    @Autowired
    public LikeListService(LikeListPersistence likeListPersistence, UserRestClient userRestClient, CompanyPersistence companyPersistence) {
        this.likeListPersistence = likeListPersistence;
        this.userRestClient = userRestClient;
        this.companyPersistence = companyPersistence;
    }

    public boolean readSharedById(UUID id) {
        return this.likeListPersistence.readSharedById(id);
    }

    public List<String> obtainSectorsByMobile(String mobile) {
        //  Obtener usuario externo
        UserDto user = this.userRestClient.readByMobile(mobile);

        //  Obtener nombres de videojuegos que le gustan al usuario
        List<String> likedVideogames = this.likeListPersistence.findVideogamesByUserId(user.getId())
                .filter(Objects::nonNull)
                .map(Videogame::getName)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        //  Obtener compañías que tengan al menos un videojuego de la LikeList
        return this.companyPersistence.readAll() // todas las compañías
                .filter(Objects::nonNull)
                .filter(company -> company.getVideoGames() != null &&
                        company.getVideoGames().stream()
                                .map(Videogame::getName)
                                .anyMatch(likedVideogames::contains)) // mínimo un videojuego coincida
                .map(Company::getSector)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }
}
