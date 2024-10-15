package es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketSeason;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class BasketSeasonEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer seasonId;
    private int startYear;
    private int endYear;
    private String league;
    @DBRef
    private List<BasketMatchEntity> basketMatchEntities;

    public BasketSeasonEntity() {
        // empty for framework
    }

    public BasketSeasonEntity(Integer seasonId, int startYear, int endYear, String league, List<BasketMatchEntity> basketMatchEntities) {
        this.id = UUID.randomUUID().toString();
        this.seasonId = seasonId;
        this.startYear = startYear;
        this.endYear = endYear;
        this.league = league;
        this.basketMatchEntities = basketMatchEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public List<BasketMatchEntity> getBasketMatchEntities() {
        return basketMatchEntities;
    }

    public void setBasketMatchEntities(List<BasketMatchEntity> basketMatchEntities) {
        this.basketMatchEntities = basketMatchEntities;
    }

    public BasketSeason toBasketSeason() {
        List<BasketMatch> matches = this.basketMatchEntities.stream()
                .map(BasketMatchEntity::toBasketMatch)
                .toList();
        return new BasketSeason(this.seasonId,this.startYear,this.endYear,this.league,matches);
    }

    @Override
    public int hashCode() {
        return seasonId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && seasonId.equals(((BasketSeasonEntity) obj).seasonId));
    }

    @Override
    public String toString() {
        return "BasketSeasonEntity{" +
                "id='" + id + '\'' +
                ", seasonId=" + seasonId +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", league='" + league + '\'' +
                ", basketMatchEntities=" + basketMatchEntities +
                '}';
    }
}