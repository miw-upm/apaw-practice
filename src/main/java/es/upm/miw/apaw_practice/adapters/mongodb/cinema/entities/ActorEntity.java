package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ActorEntity {
    @Id
    private String id;
    private String name;
    private String familyName;
    private Integer age;

    public ActorEntity(){
        //empty for framework
    }

    public ActorEntity(String name, String familyName, Integer age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.familyName = familyName;
        this.age = age;
    }

    public ActorEntity(Actor actor) {
        BeanUtils.copyProperties(actor, this);
        this.id = UUID.randomUUID().toString();
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Actor toActor() {
        return new Actor(name, familyName, age);
    }

    @Override
    public String toString() {
        return "ActorEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                '}';
    }
}
