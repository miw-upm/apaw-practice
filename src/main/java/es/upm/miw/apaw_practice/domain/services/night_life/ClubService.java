package es.upm.miw.apaw_practice.domain.services.night_life;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ClubPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.shop.ArticlePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

@Service
public class ClubService {

    private final ClubPersistence clubPersistence;
    @Autowired
    public ClubService(ClubPersistence clubPersistence) {
        this.clubPersistence = clubPersistence;
    }
    public Stream<Club> readAll(){
        return this.clubPersistence.readAll();
    }

}
