package es.upm.miw.apaw_practice.adapters.rest.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import es.upm.miw.apaw_practice.domain.services.tv_series.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TvSeriesResource.TV_SERIES)
public class TvSeriesResource {

    static final String TV_SERIES = "/tv_series";
    static final String TITLE = "/{title}";

    private final TvSeriesService tvSeriesService;

    @Autowired
    public TvSeriesResource(TvSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @DeleteMapping(TITLE)
    public void delete(@PathVariable String title) {
        this.tvSeriesService.deleteByTitle(title);
    }
}
