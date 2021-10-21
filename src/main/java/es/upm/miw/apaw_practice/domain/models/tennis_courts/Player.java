package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private String dni;
    private String name;
    private String surname;
    private Integer age;
    private List<Equipment> equipmentList;

    public Player(){
        //empty for framework
    }

    public Player(String dni){
        this.dni = dni;
        this.name = null;
        this.surname = null;
        this.age = null;
        this.equipmentList = null;
    }

    public Player(String dni, String name, String surname, Integer age){
        this(dni);
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.equipmentList = new ArrayList<>();
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String dni) {
        this.dni = dni;
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

    public void setEquipmentList(List<Equipment> equipmentList){
        this.equipmentList = equipmentList;
    }

    public void setEquipment(Equipment equipment){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return dni.equals(player.dni) && Objects.equals(name, player.name) && Objects.equals(surname, player.surname) && Objects.equals(age, player.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
