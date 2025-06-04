package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestivalBudgetUpdating;
import es.upm.miw.apaw_practice.domain.services.music_festival.MusicFestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MusicFestivalResource.FESTIVALS)
public class MusicFestivalResource {

    static final String FESTIVALS = "/music-festival/festivals";

    private final MusicFestivalService musicFestivalService;

    @Autowired
    public MusicFestivalResource(MusicFestivalService musicFestivalService) {
        this.musicFestivalService = musicFestivalService;
    }

    @PatchMapping
    public void updateBudgets(@RequestBody List<MusicFestivalBudgetUpdating> musicFestivalBudgetUpdatingList) {
        this.musicFestivalService.updateBudgets(musicFestivalBudgetUpdatingList.stream());
    }
}