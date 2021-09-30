package es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities;

import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class AnimalEntity {

    @Id
    private String id;
    private String family;
    private String diet;
    private Integer age;

    public AnimalEntity() {
        //empty from framework
    }

    public AnimalEntity(Animal animal) {
        BeanUtils.copyProperties(animal, this);
        this.id = UUID.randomUUID().toString();
    }

    public Animal toAnimal() {
        return new Animal(this.family, this.diet, this.age);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || obj != null
                && getClass() == obj.getClass()
                && (family.equals(((AnimalEntity) obj).family)
                && diet.equals(((AnimalEntity) obj).diet)
                && age.equals(((AnimalEntity) obj).age));
    }

    @Override
    public String toString() {
        return "Animal{" +
                "family='" + family + '\'' +
                ", diet='" + diet + '\'' +
                ", age=" + age +
                '}';
    }
}
