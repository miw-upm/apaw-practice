package es.upm.miw.apaw_practice.domain.models.tennis_courts;

public class Player {
    private String name;
    private String surname;
    private Integer age;

    public Player(String name, String surname, Integer age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getCompleteName(){
        return name + " " + surname;
    }

}
