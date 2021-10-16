package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class PlayerEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String name;
    private String surname;
    private Integer age;
    private List<EquipmentEntity> equipmentList;

    public PlayerEntity(){
        //empty from framework
    }

    public PlayerEntity(String dni, String name, String surname, Integer age, List<EquipmentEntity> equipmentList){
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.equipmentList = equipmentList;
    }

    public PlayerEntity(Player player){
        this.id = UUID.randomUUID().toString();
        this.dni = player.getDNI();
        this.name = player.getName();
        this.surname = player.getSurname();
        this.age = player.getAge();
        this.equipmentList = toEquipmentEntityList(player.getEquipmentList());

    }

    public String getDni(){
        return this.dni;
    }

    public void setDni(String dni){
        this.dni = dni;
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

    public void setEquipmentEntityListFromEquipment(List<Equipment> equipmentList){
        this.equipmentList = toEquipmentEntityList(equipmentList);
    }

    public static List<EquipmentEntity> toEquipmentEntityList(List<Equipment> equipmentList){
        return equipmentList.stream()
                .map(equipment -> new EquipmentEntity(equipment.getType(), equipment.getQuantity(), equipment.getPricePerUnit()))
                .collect(Collectors.toList());
    }

    public static List<Equipment> toEquipmentList(List<EquipmentEntity> equipmentList){
        return equipmentList.stream()
                .map(equipment -> new Equipment(equipment.getType(), equipment.getQuantity(), equipment.getPricePerUnit()))
                .collect(Collectors.toList());
    }

    public Player toPlayer(){
       Player player = new Player(this.dni, this.name, this.surname, this.age);
       player.setEquipmentList(toEquipmentList(this.equipmentList));
       return player;
    }
}
