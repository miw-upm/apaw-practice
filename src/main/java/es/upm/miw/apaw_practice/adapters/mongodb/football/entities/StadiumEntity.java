package es.upm.miw.apaw_practice.adapters.mongodb.football.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.UUID;

public class StadiumEntity {
    @Id
    private String id;
    private String city;
    private String name;
    private String team;
    @DBRef
    private List<MatchEntity> matchEntities;

    public StadiumEntity() {
        //empty for framework
    }

    public StadiumEntity(String city, String name, String team, List<MatchEntity> matchEntities) {
        this.id = UUID.randomUUID().toString();
        this.city = city;
        this.name = name;
        this.team = team;
        this.matchEntities = matchEntities;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MatchEntity> getMatchEntities() {
        return matchEntities;
    }

    public void setMatchEntities(List<MatchEntity> matchEntities) {
        this.matchEntities = matchEntities;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((StadiumEntity) obj).id));
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
