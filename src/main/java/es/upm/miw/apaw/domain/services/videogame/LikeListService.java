package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.models.videogame.LikeList;
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
        UserDto user = this.userRestClient.readByMobile(mobile);

        List<String> likedVideogames = this.likeListPersistence.findVideogamesByUserId(user.getId())
                .map(Videogame::getName)
                .filter(Objects::nonNull)
                .toList();

        return this.companyPersistence.readAll()
                .filter(company -> company.getVideoGames() != null)
                .filter(company -> company.getVideoGames().stream().anyMatch(videogame -> likedVideogames.contains(videogame.getName())))
                .map(Company::getSector)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }
    public List<String> obtainMobilesBySector(String sector) {

        List<Videogame> videogamesInSector = this.companyPersistence.readAll()
                .filter(company -> sector.equals(company.getSector()))
                .filter(company -> company.getVideoGames() != null)
                .flatMap(company -> company.getVideoGames().stream())
                .toList();

        List<String> videogameNamesInSector = videogamesInSector.stream()
                .map(Videogame::getName)
                .filter(Objects::nonNull)
                .toList();

        return this.likeListPersistence.readAll()
                .filter(likeList -> likeList.getGamesLiked() != null)
                .filter(likeList -> likeList.getGamesLiked().stream()
                        .anyMatch(videogame -> videogameNamesInSector.contains(videogame.getName())))
                .map(LikeList::getUser)
                .map(UserDto::getId)
                .map(this.userRestClient::readById)
                .map(UserDto::getMobile)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }
}
