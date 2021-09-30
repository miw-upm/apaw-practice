package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities;


import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class GymEntity {

    @Id
    private String id ;
    private String gymAddress;
    private String gymName ;
    @DBRef
    private List<CoachEntity> coach ;

    public GymEntity(){
        //empty
    }

    public GymEntity(String gymAddress, String gymName, List<CoachEntity> coach) {
        this.id = UUID.randomUUID().toString() ;
        this.gymAddress = gymAddress;
        this.gymName = gymName;
        this.coach = coach;
    }

    public String getId() {
        return id;
    }


    public String getGymAddress() {
        return gymAddress;
    }

    public void setGymAddress(String gymAddress) {
        this.gymAddress = gymAddress;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public List<CoachEntity> getCoach() {
        return coach;
    }

    public void setCoach(List<CoachEntity> coach) {
        this.coach = coach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GymEntity gymentity = (GymEntity) o;
        return id.equals(gymentity.id) && Objects.equals(gymAddress, gymentity.gymAddress) && Objects.equals(gymName, gymentity.gymName) && Objects.equals(coach, gymentity.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gymAddress, gymName, coach);
    }

    @Override
    public String toString() {
        return "Gymentity{" +
                "id='" + id + '\'' +
                ", gymAddress='" + gymAddress + '\'' +
                ", gymName='" + gymName + '\'' +
                ", coach=" + coach +
                '}';
    }
}
