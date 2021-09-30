package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities;

import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class TvSeriesEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private Integer year;
    private Boolean finished;
    @DBRef
    private ProducerEntity producerEntity;

    public TvSeriesEntity() {
        // empty for framework
    }

    public TvSeriesEntity(String title, Integer year, Boolean finished, ProducerEntity producerEntity) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.year = year;
        this.finished = finished;
        this.producerEntity = producerEntity;
    }

    public TvSeriesEntity(TvSeries tvSeries) {
        this.id = UUID.randomUUID().toString();
        this.title = tvSeries.getTitle();
        this.year = tvSeries.getYear();
        this.finished = tvSeries.isFinished();
        this.producerEntity = new ProducerEntity(tvSeries.getProducer());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean isFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public ProducerEntity getProducerEntity() {
        return this.producerEntity;
    }

    public void setProducerEntity(ProducerEntity producerEntity) {
        this.producerEntity = producerEntity;
    }

    public TvSeries toTvSeries() {
        TvSeries tvSeries = new TvSeries();
        tvSeries.setProducer(this.producerEntity.toProducer());
        tvSeries.setYear(this.year);
        tvSeries.setTitle(this.title);
        tvSeries.setFinished(this.finished);
        return tvSeries;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass()
                || ((title.equals(((TvSeriesEntity) obj).title))
                && (year.equals(((TvSeriesEntity) obj).year))
                && (finished == ((TvSeriesEntity) obj).finished)
                && (producerEntity.equals(((TvSeriesEntity) obj).producerEntity)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, finished, producerEntity);
    }

    @Override
    public String toString() {
        return "TvSeries{" +
                "id='" + this.id + '\'' +
                ", title='" + this.title + '\'' +
                ", year=" + this.year +
                ", finished=" + this.finished +
                ", producerEntity=" + this.producerEntity +
                '}';
    }

}
