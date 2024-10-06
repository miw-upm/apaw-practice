package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.UUID;

public class WushuSchoolEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String location;
    private Boolean affiliated;
    @DBRef
    private List<Competitor> competitorsEntities;

    public WushuSchoolEntity() {
        //empty for framework
    }
    public WushuSchoolEntity(String name, String location, Boolean affiliated, List<Competitor> competitorsEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.location = location;
        this.affiliated = affiliated;
        this.competitorsEntities = competitorsEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getAffiliated() {
        return affiliated;
    }

    public void setAffiliated(Boolean affiliated) {
        this.affiliated = affiliated;
    }

    public List<Competitor> getCompetitorsEntities() {
        return competitorsEntities;
    }

    public void setCompetitorsEntities(List<Competitor> competitorsEntities) {
        this.competitorsEntities = competitorsEntities;
    }

    @Override
    public String toString() {
        return "WushuSchoolEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", affiliated=" + affiliated +
                ", competitorsEntities=" + competitorsEntities +
                '}';
    }
}
