package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
@Document
public class PlayerEntity {
    @Id
    private String id;
    private String name;
    private String surname;
    private Integer age;
    private List<EquipmentEntity> equipmentList;

    public PlayerEntity(){
        //empty from framework
    }

    public PlayerEntity(String name, String surname, Integer age, List<EquipmentEntity> equipmentList){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.equipmentList = equipmentList;
    }

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

    public List<EquipmentEntity> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<EquipmentEntity> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
