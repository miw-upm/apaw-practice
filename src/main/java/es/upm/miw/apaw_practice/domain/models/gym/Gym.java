package es.upm.miw.apaw_practice.domain.models.gym;

import java.util.List;

public class Gym {
    private String gymAddress;
    private String gymName ;
    private List<Coach> coach ;

    public Gym(){
        //empty for framework
    }

    public Gym(String gymAddress, String gymName, List<Coach> coach) {
        this.gymAddress = gymAddress;
        this.gymName = gymName;
        this.coach = coach;
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

    public List<Coach> getCoach() {
        return this.coach;
    }

    public void setCoach(List<Coach> coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "gymAddress='" + gymAddress + '\'' +
                ", gymName='" + gymName + '\'' +
                ", Coach=" + coach +
                '}';
    }
}
