package es.upm.miw.apaw_practice.domain.models.football;

import java.util.List;

public class Stadium {
    private String city;
    private String name;
    private String team;
    private List<Match> matches;

    public Stadium() {
        //empty for framework
    }

    public Stadium(String city, String name, String team, List<Match> matches) {
        this.city = city;
        this.name = name;
        this.team = team;
        this.matches = matches;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "city=" + city +
                ", name=" + name +
                ", team=" + team +
                '}';
    }
}
