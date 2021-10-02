package es.upm.miw.apaw_practice.domain.models.football;

public class Stadium {
    private String city;
    private String name;
    private String team;

    public Stadium() {
        //empty for framework
    }

    public Stadium(String city, String name, String team) {
        this.city = city;
        this.name = name;
        this.team = team;
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

    @Override
    public String toString() {
        return "Stadium{" +
                "city=" + city +
                ", name=" + name +
                ", team=" + team +
                '}';
    }
}
