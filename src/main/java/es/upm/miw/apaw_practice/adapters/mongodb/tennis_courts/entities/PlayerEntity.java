package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;

import java.util.List;

public class PlayerEntity {
    private String name;
    private String surname;
    private Integer age;
    private List<Equipment> equipmentList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
