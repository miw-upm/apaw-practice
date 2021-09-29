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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
