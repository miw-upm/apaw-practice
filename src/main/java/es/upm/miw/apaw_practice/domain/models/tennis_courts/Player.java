package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String surname;
    private Integer age;
    private List<Equipment> equipmentList;

    public Player(String name, String surname, Integer age){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.equipmentList = new ArrayList<>();
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

    public void setEquipmentList(Equipment equipment){
        this.equipmentList.add(equipment);
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + this.name + '\'' +
                ", surname='" + this.surname + '\'' +
                ", age=" + this.age +
                ", equipmentList=" + this.equipmentList +
                '}';
    }
}
