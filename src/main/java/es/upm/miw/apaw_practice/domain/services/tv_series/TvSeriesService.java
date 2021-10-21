package es.upm.miw.apaw_practice.domain.services.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeriesFinishedUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.tv_series.TvSeriesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TvSeriesService {

    private final TvSeriesPersistence tvSeriesPersistence;

    @Autowired
    public TvSeriesService(TvSeriesPersistence tvSeriesPersistence) {
        this.tvSeriesPersistence = tvSeriesPersistence;
    }

    public void deleteByTitle(String title) {
        this.tvSeriesPersistence.deleteByTitle(title);
    }

    public void updateFinished(TvSeriesFinishedUpdating tvSeriesFinishedUpdatingList) {
        TvSeries tvSeries = this.tvSeriesPersistence.read(tvSeriesFinishedUpdatingList.getTitle());
        tvSeries.setFinished(tvSeriesFinishedUpdatingList.getFinished());
        this.tvSeriesPersistence.update(tvSeries.getTitle(), tvSeries);
    }
}
