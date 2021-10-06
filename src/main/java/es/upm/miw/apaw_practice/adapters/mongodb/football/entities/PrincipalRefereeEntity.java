package es.upm.miw.apaw_practice.adapters.mongodb.football.entities;

import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class PrincipalRefereeEntity {
    @Id
    private String id;
    private String name;
    private String cityBorn;
    private Integer age;

    public PrincipalRefereeEntity() {
        //empty for framework
    }

    public PrincipalRefereeEntity(String name, String cityBorn, Integer age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cityBorn = cityBorn;
        this.age = age;
    }

    public PrincipalRefereeEntity(PrincipalReferee principalReferee) {
        BeanUtils.copyProperties(principalReferee, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityBorn() {
        return cityBorn;
    }

    public void setCityBorn(String cityBorn) {
        this.cityBorn = cityBorn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PrincipalReferee toPrincipalReferee() {
        PrincipalReferee principalReferee = new PrincipalReferee();
        BeanUtils.copyProperties(this, principalReferee);
        return principalReferee;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((PrincipalRefereeEntity) obj).id));
    }

    @Override
    public String toString() {
        return "PrincipalReferee{" +
                "name=" + name +
                ", cityBorn=" + cityBorn +
                ", age=" + age +
                '}';
    }


}
