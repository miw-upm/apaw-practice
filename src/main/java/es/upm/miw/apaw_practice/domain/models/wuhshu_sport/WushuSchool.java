package es.upm.miw.apaw_practice.domain.models.wuhshu_sport;

import java.util.List;

public class WushuSchool {

    private String name;
    private String location;
    private Boolean affiliated;
    private List<Competitor> competitors;

    public WushuSchool() {
        //empty for framework
    }

    public WushuSchool(String name, String location, Boolean isAffiliated, List<Competitor> competitors) {
        this.name = name;
        this.location = location;
        this.affiliated = isAffiliated;
        this.competitors = competitors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean isAffiliated() {
        return affiliated;
    }

    public void setAffiliated(Boolean affiliated) {
        this.affiliated = affiliated;
    }

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
    }

    @Override
    public String toString() {
        return "WushuSchool{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", affiliated=" + affiliated +
                ", competitors=" + competitors +
                '}';
    }
}
