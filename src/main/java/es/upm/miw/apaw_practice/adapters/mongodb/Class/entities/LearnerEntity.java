package es.upm.miw.apaw_practice.adapters.mongodb.Class.entities;

import es.upm.miw.apaw_practice.domain.models.Class.Learner;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;

public class LearnerEntity {

    @Id
    private String id;
    private String name;
    private int age;
    private boolean isSpanish;

    public LearnerEntity() {
        //empty for framework
    }

    public LearnerEntity(String name, int age, boolean isSpanish) {
        this.name = name;
        this.age = age;
        this.isSpanish = isSpanish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsSpanish() {
        return isSpanish;
    }

    public void setIsSpanish(boolean isSpanish) {
        this.isSpanish = isSpanish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Learner toLearner(){
        Learner learner = new Learner();
        BeanUtils.copyProperties(this, learner);
        return learner;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((LearnerEntity) obj).id));
    }

    @Override
    public String toString() {
        return "Album {" +
                "name =" + name + '\'' +
                ", age =" + age + '\'' +
                ", Is_spanish ='" + isSpanish + '\'' +
                '}';
    }
}
