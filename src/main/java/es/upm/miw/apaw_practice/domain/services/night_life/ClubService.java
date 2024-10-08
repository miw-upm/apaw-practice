package es.upm.miw.apaw_practice.domain.services.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ClubPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
