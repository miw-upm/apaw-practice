package es.upm.miw.apaw_practice.domain.services.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestivalBudgetUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.MusicFestivalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class MusicFestivalService {

    private final MusicFestivalPersistence musicFestivalPersistence;

    @Autowired
    public MusicFestivalService(MusicFestivalPersistence musicFestivalPersistence) {
        this.musicFestivalPersistence = musicFestivalPersistence;
    }

    public void updateBudgets(Stream<MusicFestivalBudgetUpdating> budgetUpdatingList) {
        budgetUpdatingList.map(budgetUpdating -> {
                    MusicFestival festival = this.musicFestivalPersistence.readByName(budgetUpdating.getName());
                    festival.setBudget(budgetUpdating.getBudget());
                    return festival;
                })
                .forEach(festival -> this.musicFestivalPersistence.update(festival.getName(), festival));
    }
}