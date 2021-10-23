package es.upm.miw.apaw_practice.adapters.rest.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.Episode;
import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeriesFinishedUpdating;
import es.upm.miw.apaw_practice.domain.services.tv_series.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TvSeriesResource.TV_SERIES)
public class TvSeriesResource {

    static final String TV_SERIES = "/tv_series";
    static final String TITLE = "/{title}";
    static final String EPISODES = "/episodes";

    private final TvSeriesService tvSeriesService;

    @Autowired
    public TvSeriesResource(TvSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @DeleteMapping(TITLE)
    public void delete(@PathVariable String title) {
        this.tvSeriesService.deleteByTitle(title);
    }

    @PatchMapping
    public void updateFinished(@RequestBody TvSeriesFinishedUpdating tvSeriesFinishedUpdating) {
        this.tvSeriesService.updateFinished(tvSeriesFinishedUpdating);
    }

    @PostMapping(TITLE + EPISODES)
    public void createEpisode(@PathVariable String title, @RequestBody Episode episode) {
        this.tvSeriesService.createEpisode(title, episode);
    }
}
