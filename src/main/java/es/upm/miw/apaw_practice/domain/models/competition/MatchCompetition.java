package es.upm.miw.apaw_practice.domain.models.competition;

import java.time.LocalDateTime;

public class MatchCompetition {

    private String id;
    private LocalDateTime dayMatch;
    private String cityMatch;

    public MatchCompetition() {
        // empty for framework
    }

    public MatchCompetition(String id, LocalDateTime dayMatch, String cityMatch) {
        this.id = id;
        this.dayMatch = dayMatch;
        this.cityMatch = cityMatch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDayMatch() {
        return dayMatch;
    }

    public void setDayMatch(LocalDateTime dayMatch) {
        this.dayMatch = dayMatch;
    }

    public String getCityMatch() {
        return cityMatch;
    }

    public void setCityMatch(String cityMatch) {
        this.cityMatch = cityMatch;
    }

    @Override
    public String toString() {
        return "MatchCompetition{" +
                "id='" + id + '\'' +
                ", dayMatch=" + dayMatch +
                ", cityMatch='" + cityMatch + '\'' +
                '}';
    }
}
