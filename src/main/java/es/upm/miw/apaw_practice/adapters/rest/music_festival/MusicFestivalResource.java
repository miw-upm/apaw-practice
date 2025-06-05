package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.Concert;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestivalBudgetUpdating;
import es.upm.miw.apaw_practice.domain.services.music_festival.MusicFestivalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MusicFestivalResource.FESTIVALS)
public class MusicFestivalResource {

    static final String FESTIVALS = "/music-festival/festivals";
    static final String NAME_ID = "/{name}";
    static final String CONCERTS = "/concerts";

    private final MusicFestivalService musicFestivalService;

    @Autowired
    public MusicFestivalResource(MusicFestivalService musicFestivalService) {
        this.musicFestivalService = musicFestivalService;
    }

    @PatchMapping
    public void updateBudgets(@RequestBody List<MusicFestivalBudgetUpdating> musicFestivalBudgetUpdatingList) {
        this.musicFestivalService.updateBudgets(musicFestivalBudgetUpdatingList.stream());
    }

    @PutMapping(NAME_ID + CONCERTS)
    public MusicFestival updateConcerts(@PathVariable String name, @RequestBody List<Concert> concertList) {
        return this.musicFestivalService.updateConcerts(name, concertList);
    }
}